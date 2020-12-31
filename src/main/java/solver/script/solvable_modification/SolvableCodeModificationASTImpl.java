package solver.script.solvable_modification;

import ast.interfaces.*;

import java.util.Arrays;
import java.util.List;

public class SolvableCodeModificationASTImpl implements SolvableCodeModificationAST {

	private NodeBuilder nodeBuilder;
	private ExpressionDelegate solverHolePlaceholder;

	private final String noChangeRawValue = "0";

	public SolvableCodeModificationASTImpl(
			NodeBuilder nodeBuilder, ExpressionDelegate solverHolePlaceholder) {
		this.nodeBuilder = nodeBuilder;
		this.solverHolePlaceholder = solverHolePlaceholder;
	}

	/*
	Creates code in the form, where a placeholder variable represents '??':
	int coeff_1_change = ??
	int coeff_1() {
		if (??) {
			return 0;
		}
		return coeff_1_change;
	}
	 */
	@Override
	public List<NodeDelegate> getInitializationCode(SolvableCodeModification modification) {
		TypeDelegate modificationType = modification.originalCode.getType();
		//  coeff_1
		String methodName = modification.id.method;
		//  coeff_1_change
		String changeVarName = modification.id.changeVariable;

		//  int coeff_1_change = ??;
		DeclarationStatementDelegate changeVarDecl = this.nodeBuilder.buildDeclarationStatement(changeVarName,
				modificationType,
				solverHolePlaceholder);

		MethodDelegate changeMethod = this.createMethod(modificationType,
				methodName,
				changeVarName);

		return Arrays.asList(new NodeDelegate[] { changeVarDecl, changeMethod });
	}

	//  int coeff_1() { ... }
	private MethodDelegate createMethod(
			TypeDelegate type, String methodName, String changeVarName) {
		IfStatementDelegate noChangeIfStatement = this.createNoChangeIfStatement();

		// return coeff_1_change;
		StatementDelegate changeReturn
				= this.nodeBuilder.buildReturnStatement(this.nodeBuilder.buildExpressionFromText(
				changeVarName));

		CodeBlockDelegate methodBody = this.nodeBuilder.buildEmptyCodeBlock();
		methodBody.addStatements(noChangeIfStatement, changeReturn);

		return this.nodeBuilder.buildMethod(type, methodName, null, methodBody);
	}

	// if (??) { return 0; }
	private IfStatementDelegate createNoChangeIfStatement() {
		StatementDelegate noChangeReturn = this.getNoChangeReturn();

		CodeBlockDelegate noChangeIfBody = this.nodeBuilder.buildEmptyCodeBlock();
		noChangeIfBody.addStatement(noChangeReturn);

		return this.nodeBuilder.buildIfStatement(this.solverHolePlaceholder, noChangeIfBody);
	}

	//  return 0;
	private StatementDelegate getNoChangeReturn() {
		ExpressionDelegate noChangeValue
				= this.nodeBuilder.buildExpressionFromText(this.noChangeRawValue);
		return this.nodeBuilder.buildReturnStatement(noChangeValue);
	}

	@Override
	public ExpressionDelegate getSolvableCode(SolvableCodeModification modification) {
		ExpressionDelegate solvableMethodCall
				= this.nodeBuilder.buildMethodCall(modification.id.method);
		return this.nodeBuilder.buildBinaryExpression(
				modification.originalCode,
				BinaryOperator.ADD,
				solvableMethodCall);
	}
}
