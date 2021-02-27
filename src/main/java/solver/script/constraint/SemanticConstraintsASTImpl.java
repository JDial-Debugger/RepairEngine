package solver.script.constraint;

import ast.interfaces.*;
import solver.script.state_record.StateRecord;
import solver.script.state_record.StateRecordAST;
import util.Constants;

public class SemanticConstraintsASTImpl implements SemanticConstraintsAST {

	private StateRecordAST stateRecordAST;
	private NodeBuilder nodeBuilder;
	private int executionLength;
	private int exampleCount;

	private static final String ID = Constants.SCRIPT_ID_PREFIX + "semantic_distance";
	private static final String STATE_SUFFIX = "_state";

	private static final String EXAMPLE_INDEX_NAME = "j";
	private static final String EXECUTION_INDEX_NAME = "j";

	public SemanticConstraintsASTImpl(
			StateRecordAST stateRecordAST,
			NodeBuilder nodeBuilder,
			int executionLength,
			int exampleCount) {
		this.stateRecordAST = stateRecordAST;
		this.nodeBuilder = nodeBuilder;
		this.exampleCount = exampleCount;
		this.executionLength = executionLength;
	}

	@Override
	public Statement getStateRecordChangedConstraint(
			StateRecord stateRecord, StateRecord originalStateRecord) {

		DeclarationStatement outerInitializer = this.getLoopInitializer(EXAMPLE_INDEX_NAME);
		Expression outerCondition = this.getLoopCondition(EXAMPLE_INDEX_NAME, this.exampleCount);
		Statement outerIncrementer = this.getLoopIncrementer(EXAMPLE_INDEX_NAME);

		ForStatement innerLoop = this.getExecutionLoop(stateRecord, originalStateRecord);

		return this.nodeBuilder.buildForStatement(
				outerInitializer,
				outerCondition,
				outerIncrementer,
				innerLoop);
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

	private ForStatement getExecutionLoop(
			StateRecord changedStateRecord,
			StateRecord originalStateRecord) {
		DeclarationStatement innerInitializer = this.getLoopInitializer(EXECUTION_INDEX_NAME);
		Expression innerCondition = this.getLoopCondition(EXECUTION_INDEX_NAME, this.exampleCount);
		Statement innerIncrementer = this.getLoopIncrementer(EXECUTION_INDEX_NAME);

		Statement assignChangeStatement = this.getAddChangeFromOriginalStateStmt(
				changedStateRecord,
				originalStateRecord);

		return this.nodeBuilder.buildForStatement(
				innerInitializer,
				innerCondition,
				innerIncrementer,
				assignChangeStatement);
	}

	private Statement getAddChangeFromOriginalStateStmt(
			StateRecord changedStateRecord,
			StateRecord originalStateRecord) {
		Expression compareChangedToOriginalState
				= this.getCompareStateRecordExpr(changedStateRecord, originalStateRecord);
		Expression assignChange = this.nodeBuilder.buildAssignExpression(
				this.getConstraintReferenceExpression(),
				AssignOperator.ADD,
				compareChangedToOriginalState);
		return this.nodeBuilder.buildExpressionStatement(assignChange);
	}

	private Expression getCompareStateRecordExpr(
			StateRecord changedStateRecord,
			StateRecord originalStateRecord) {
		Expression changedArrayAccess = this.getStateRecordAccessExpression(
				changedStateRecord,
				EXAMPLE_INDEX_NAME,
				EXECUTION_INDEX_NAME);
		Expression originalArrayAccess = this.getStateRecordAccessExpression(
				originalStateRecord,
				EXAMPLE_INDEX_NAME,
				EXECUTION_INDEX_NAME);
		return this.nodeBuilder.buildBinaryExpression(
				changedArrayAccess,
				BinaryOperator.NOT_EQUALS,
				originalArrayAccess);
	}

	private Expression getStateRecordAccessExpression(
			StateRecord stateRecord,
			String exampleIndexName,
			String executionIndexName) {

		String changedRecordArrayText = this.getChangedStateRecordArrayId(stateRecord);
		Expression changedArray = this.nodeBuilder.buildExpressionFromText(changedRecordArrayText);

		Expression exampleCounterRef = this.nodeBuilder.buildExpressionFromText(exampleIndexName);
		Expression changedState = this.nodeBuilder.buildArrayAccessExpression(
				changedArray,
				exampleCounterRef);

		Expression executionCounterRef
				= this.nodeBuilder.buildExpressionFromText(executionIndexName);
		return this.nodeBuilder.buildArrayAccessExpression(
				changedState,
				executionCounterRef);
	}

	private String getChangedStateRecordArrayId(StateRecord stateRecord) {
		return Constants.SCRIPT_ID_PREFIX
				+ stateRecord.functionName
				+ "_"
				+ stateRecord.variableType
				+ STATE_SUFFIX;
	}

	private String getOriginalStateRecordArrayId(StateRecord stateRecord) {
		return Constants.SCRIPT_ID_PREFIX
				+ stateRecord.functionName
				+ "_"
				+ stateRecord.variableType
				+ "_original_"
				+ STATE_SUFFIX;
	}

	@Override
	public Expression getConstraintReferenceExpression() {
		return this.nodeBuilder.buildExpressionFromText(ID);
	}
}
