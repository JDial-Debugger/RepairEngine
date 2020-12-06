package solver.script.solvable_modification;

import ast.interfaces.*;

import java.util.Arrays;
import java.util.List;

public class SolvableCodeModificationASTImpl implements SolvableCodeModificationAST {

	private NodeFactory nodeFactory;
	private ExpressionDelegate solverHolePlaceholder;

	private final String noChangeRawValue = "0";

	public SolvableCodeModificationASTImpl(
			NodeFactory nodeFactory, ExpressionDelegate solverHolePlaceholder) {
		this.nodeFactory = nodeFactory;
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
		DeclarationStatementDelegate changeVarDecl = this.nodeFactory.getDeclarationStatement(changeVarName,
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
				= this.nodeFactory.getReturnStatement(this.nodeFactory.getExpressionFromText(
				changeVarName));

		CodeBlockDelegate methodBody = this.nodeFactory.getEmptyCodeBlock();
		methodBody.addStatements(noChangeIfStatement, changeReturn);

		return this.nodeFactory.getMethod(type, methodName, null, methodBody);
	}

	// if (??) { return 0; }
	private IfStatementDelegate createNoChangeIfStatement() {
		StatementDelegate noChangeReturn = this.getNoChangeReturn();

		CodeBlockDelegate noChangeIfBody = this.nodeFactory.getEmptyCodeBlock();
		noChangeIfBody.addStatement(noChangeReturn);

		return this.nodeFactory.getIfStatement(this.solverHolePlaceholder, noChangeIfBody);
	}

	//  return 0;
	private StatementDelegate getNoChangeReturn() {
		ExpressionDelegate noChangeValue
				= this.nodeFactory.getExpressionFromText(this.noChangeRawValue);
		return this.nodeFactory.getReturnStatement(noChangeValue);
	}

	@Override
	public ExpressionDelegate getSolvableCode(SolvableCodeModification modification) {
		ExpressionDelegate solvableMethodCall
				= this.nodeFactory.getMethodCall(modification.id.method);
		return this.nodeFactory.getBinaryExpression(
				modification.originalCode,
				BinaryOperator.ADD,
				solvableMethodCall);
	}
}
