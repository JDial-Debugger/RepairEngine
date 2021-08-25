package solver.script.constraint;

import ast.interfaces.*;
import solver.script.state_record.StateRecord;
import util.Constants;

public class SemanticConstraintsASTImpl implements SemanticConstraintsAST {

	private NodeBuilder nodeBuilder;
	private int executionLength;
	private int exampleCount;

	private static final String ID = Constants.SCRIPT_ID_PREFIX + "semantic_distance";
	private static final String STATE_SUFFIX = "_state";

	private static final String EXAMPLE_INDEX_NAME = "i";
	private static final String EXECUTION_INDEX_NAME = "j";

	public SemanticConstraintsASTImpl(
			NodeBuilder nodeBuilder,
			int exampleCount,
			int executionLength) {
		this.nodeBuilder = nodeBuilder;
		this.exampleCount = exampleCount;
		this.executionLength = executionLength;
	}

	@Override
	public Statement getStateRecordChangedConstraint(StateRecord stateRecord) {

		DeclarationStatement outerInitializer = this.getLoopInitializer(EXAMPLE_INDEX_NAME);
		Expression outerCondition = this.getLoopCondition(EXAMPLE_INDEX_NAME, this.exampleCount);
		Statement outerIncrementer = this.getLoopIncrementer(EXAMPLE_INDEX_NAME);

		ForStatement innerLoop = this.getExecutionLoop(stateRecord);

		return this.nodeBuilder.buildForStatement(
				outerInitializer,
				outerCondition,
				outerIncrementer,
				innerLoop);
	}


	private DeclarationStatement getLoopInitializer(String counterName) {
		Type type = this.nodeBuilder.buildType(PrimitiveType.INT);
		LiteralExpression initValueExpr = this.nodeBuilder.buildLiteralIntExpression(0);
		return this.nodeBuilder.buildDeclarationStatement(
				counterName,
				type,
				initValueExpr);
	}

	private Expression getLoopCondition(String counterName, int iterationsCount) {
		Expression counterReferenceInCondition = this.nodeBuilder.buildExpressionFromText(
				counterName);
		LiteralExpression iterationsCountExpr = this.nodeBuilder.buildLiteralIntExpression(
				iterationsCount);
		return this.nodeBuilder.buildBinaryExpression(
				counterReferenceInCondition,
				BinaryOperator.LESS,
				iterationsCountExpr);
	}

	private Statement getLoopIncrementer(String counterName) {
		Expression counterReference = this.nodeBuilder.buildExpressionFromText(
				counterName);
		Expression outerIncrementer = this.nodeBuilder.buildUnaryExpression(
				counterReference,
				UnaryOperator.PRE_INCREMENT);
		return this.nodeBuilder.buildExpressionStatement(outerIncrementer);
	}

	private ForStatement getExecutionLoop(StateRecord changedStateRecord) {
		DeclarationStatement innerInitializer = this.getLoopInitializer(EXECUTION_INDEX_NAME);
		Expression innerCondition = this.getLoopCondition(
				EXECUTION_INDEX_NAME,
				this.executionLength);
		Statement innerIncrementer = this.getLoopIncrementer(EXECUTION_INDEX_NAME);

		Statement assignChangeStatement
				= this.getAddChangeFromOriginalStateStmt(changedStateRecord);

		return this.nodeBuilder.buildForStatement(
				innerInitializer,
				innerCondition,
				innerIncrementer,
				assignChangeStatement);
	}

	private Statement getAddChangeFromOriginalStateStmt(StateRecord changedStateRecord) {
		Expression compareChangedToOriginalState
				= this.getCompareStateRecordExpr(changedStateRecord);
		Expression assignChange = this.nodeBuilder.buildAssignExpression(
				this.getConstraintReferenceExpression(),
				AssignOperator.ADD,
				compareChangedToOriginalState);
		return this.nodeBuilder.buildExpressionStatement(assignChange);
	}

	private Expression getCompareStateRecordExpr(StateRecord stateRecord) {
		String changedArrayAccessText = this.getChangedStateRecordArrayId(stateRecord);
		Expression changedArrayAccess = this.getStateRecordAccessExpression(changedArrayAccessText);

		String originalArrayAccessText = this.getOriginalStateRecordArrayId(stateRecord);
		Expression originalArrayAccess
				= this.getStateRecordAccessExpression(originalArrayAccessText);

		return this.nodeBuilder.buildBinaryExpression(
				changedArrayAccess,
				BinaryOperator.NOT_EQUALS,
				originalArrayAccess);
	}

	private String getChangedStateRecordArrayId(StateRecord stateRecord) {
		return Constants.SCRIPT_ID_PREFIX
				+ stateRecord.functionName
				+ "_"
				+ stateRecord.variableName
				+ STATE_SUFFIX;
	}

	private Expression getStateRecordAccessExpression(String arrayText) {

		Expression changedArray = this.nodeBuilder.buildExpressionFromText(arrayText);

		Expression exampleCounterRef = this.nodeBuilder.buildExpressionFromText(EXAMPLE_INDEX_NAME);
		Expression changedState = this.nodeBuilder.buildArrayAccessExpression(
				changedArray,
				exampleCounterRef);

		Expression executionCounterRef = this.nodeBuilder.buildExpressionFromText(
				EXECUTION_INDEX_NAME);
		return this.nodeBuilder.buildArrayAccessExpression(
				changedState,
				executionCounterRef);
	}

	private String getOriginalStateRecordArrayId(StateRecord stateRecord) {
		return Constants.SCRIPT_ID_PREFIX
				+ stateRecord.functionName
				+ "_"
				+ stateRecord.variableName
				+ "_original"
				+ STATE_SUFFIX;
	}

	@Override
	public Expression getConstraintReferenceExpression() {
		return this.nodeBuilder.buildExpressionFromText(ID);
	}
}
