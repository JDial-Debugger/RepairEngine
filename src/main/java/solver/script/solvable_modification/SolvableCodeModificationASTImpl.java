package solver.script.solvable_modification;

import ast.interfaces.*;

import java.util.Arrays;
import java.util.List;

public class SolvableCodeModificationASTImpl implements SolvableCodeModificationAST {

	private NodeBuilder nodeBuilder;
	private Expression solverHolePlaceholder;

	private final String noChangeRawValue = "0";

	public SolvableCodeModificationASTImpl(
			NodeBuilder nodeBuilder, Expression solverHolePlaceholder) {
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
	public List<Node> getInitializationCode(SolvableCodeModification modification) {
		Type modificationType = modification.originalCode.getType();
		//  coeff_1
		String methodName = modification.id.method;
		//  coeff_1_change
		String changeVarName = modification.id.changeVariable;

		//  int coeff_1_change = ??;
		DeclarationStatement changeVarDecl = this.nodeBuilder.buildDeclarationStatement(changeVarName,
				modificationType,
				solverHolePlaceholder);

		Method changeMethod = this.createMethod(modificationType,
				methodName,
				changeVarName);

		return Arrays.asList(new Node[] { changeVarDecl, changeMethod });
	}

	//  int coeff_1() { ... }
	private Method createMethod(
			Type type, String methodName, String changeVarName) {
		IfStatement noChangeIfStatement = this.createNoChangeIfStatement();

		// return coeff_1_change;
		Statement changeReturn
				= this.nodeBuilder.buildReturnStatement(this.nodeBuilder.buildExpressionFromText(
				changeVarName));

		CodeBlock methodBody = this.nodeBuilder.buildEmptyCodeBlock();
		methodBody.addStatements(noChangeIfStatement, changeReturn);

		return this.nodeBuilder.buildMethod(type, methodName, null, methodBody);
	}

	// if (??) { return 0; }
	private IfStatement createNoChangeIfStatement() {
		Statement noChangeReturn = this.getNoChangeReturn();

		CodeBlock noChangeIfBody = this.nodeBuilder.buildEmptyCodeBlock();
		noChangeIfBody.addStatement(noChangeReturn);

		return this.nodeBuilder.buildIfStatement(this.solverHolePlaceholder, noChangeIfBody);
	}

	//  return 0;
	private Statement getNoChangeReturn() {
		Expression noChangeValue
				= this.nodeBuilder.buildExpressionFromText(this.noChangeRawValue);
		return this.nodeBuilder.buildReturnStatement(noChangeValue);
	}

	@Override
	public Expression getSolvableCode(SolvableCodeModification modification) {
		Expression solvableMethodCall
				= this.nodeBuilder.buildMethodCall(modification.id.method);
		return this.nodeBuilder.buildBinaryExpression(
				modification.originalCode,
				BinaryOperator.ADD,
				solvableMethodCall);
	}
}
