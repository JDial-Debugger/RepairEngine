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

		DeclarationStatement outerInitializer = this.getLoopInitializer(outerCounterName);
		Expression outerCondition = this.getLoopCondition(outerCounterName, this.exampleCount);
		Statement outerIncrementer = this.getLoopIncrementer(outerCounterName);

		DeclarationStatement innerInitializer = this.getLoopInitializer(outerCounterName);
		Expression innerCondition = this.getLoopCondition(outerCounterName, this.exampleCount);
		Statement innerIncrementer = this.getLoopIncrementer(outerCounterName);

		//Expression changedState = this.nodeBuilder.buildarr
		//Expression compareChangedToOriginalState = this.nodeBuilder.buildBinaryExpression()

		//this.nodeBuilder.buildForStatement(outerInitializer, outerConditon, outerIncrementer);
		return null;
	}

	private DeclarationStatement getLoopInitializer(String counterName) {
		return this.nodeBuilder.buildDeclarationStatement(
				counterName,
				this.nodeBuilder.buildType(
						PrimitiveType.INT),
				this.nodeBuilder.buildLiteralIntExpression(0));
	}

	private Expression getLoopCondition(String counterName, int iterationsCount) {
		Expression counterReferenceInCondition = this.nodeBuilder.buildExpressionFromText(
				counterName);
		return this.nodeBuilder.buildBinaryExpression(
				counterReferenceInCondition,
				BinaryOperator.LESS,
				this.nodeBuilder.buildLiteralIntExpression(iterationsCount));
	}

	private Statement getLoopIncrementer(String counterName) {
		Expression counterReference = this.nodeBuilder.buildExpressionFromText(
				counterName);
		Expression outerIncrementer = this.nodeBuilder.buildUnaryExpression(
				counterReference,
				UnaryOperator.PRE_INCREMENT);
		return this.nodeBuilder.buildExpressionStatement(outerIncrementer);
	}

	@Override
	public Expression getConstraintReferenceExpression() {
		return null;
	}
}
