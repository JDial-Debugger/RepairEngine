package solver.script.state_record;

import ast.interfaces.ExpressionStatement;
import ast.interfaces.NodeBuilder;
import ast.interfaces.Statement;
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
	public ExpressionStatement getRecordStatement(StateRecord record) {
		return null;
	}

	@Override
	public Statement getInitializationStatement(
			StateRecord record, List<ProgramTrace> traces) {
		return null;
	}
}
