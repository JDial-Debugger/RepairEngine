package solver.script.constraint;

import ast.interfaces.*;
import solver.script.state_record.StateRecord;
import solver.script.state_record.StateRecordAST;

public class SemanticConstraintsASTImpl implements SemanticConstraintsAST {

	private StateRecordAST stateRecordAST;
	private NodeBuilder nodeBuilder;
	private int executionLength;
	private int exampleCount;

	private Expression constraintReference;

	private static final String ID = "__JDIAL__semantic_distance";

	public SemanticConstraintsASTImpl(
			StateRecordAST stateRecordAST,
			NodeBuilder nodeBuilder,
			int executionLength,
			int exampleCount) {
		this.stateRecordAST = stateRecordAST;
		this.nodeBuilder = nodeBuilder;
		this.constraintReference = this.nodeBuilder.buildExpressionFromText(ID);
		this.exampleCount = exampleCount;
		this.executionLength = executionLength;
	}

	@Override
	public Statement getStateRecordChangedConstraint(
			StateRecord stateRecord, StateRecord originalStateRecord) {
		String outerCounterName = "i";
		DeclarationStatement outerInitializer = this.nodeBuilder.buildDeclarationStatement(
				outerCounterName,
				this.nodeBuilder.buildType(
						PrimitiveType.INT),
				this.nodeBuilder.buildLiteralIntExpression(0));
		Expression counterReference = this.nodeBuilder.buildExpressionFromText(outerCounterName);
		Expression outerCondiiton = this.nodeBuilder.buildBinaryExpression(
				counterReference,
				BinaryOperator.LESS,
				this.nodeBuilder.buildLiteralIntExpression(this.exampleCount));
		//this.nodeBuilder.buildForStatement();
		return null;
	}

	@Override
	public Expression getConstraintReferenceExpression() {
		return null;
	}
}
