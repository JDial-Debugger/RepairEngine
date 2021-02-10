package solver.script.state_record;

import ast.interfaces.ExpressionStatementDelegate;
import ast.interfaces.NodeBuilder;
import ast.interfaces.StatementDelegate;
import data.ProgramTrace;

import java.util.List;

public class OriginalStateRecordASTImpl implements  StateRecordAST {

	private NodeBuilder nodeBuilder;
	private VariableTraceExtractor traceExtractor;

	public OriginalStateRecordASTImpl(NodeBuilder nodeBuilder, VariableTraceExtractor traceExtractor) {
		this.nodeBuilder = nodeBuilder;
		this.traceExtractor = traceExtractor;
	}

	@Override
	public ExpressionStatementDelegate getRecordStatement(StateRecord record) {
		return null;
	}

	@Override
	public StatementDelegate getInitializationStatement(
			StateRecord record, List<ProgramTrace> traces) {
		return null;
	}
}
