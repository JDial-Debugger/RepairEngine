package solver.script.state_record;

import ast.interfaces.DeclarationStatementDelegate;
import ast.interfaces.ExpressionStatementDelegate;
import ast.interfaces.NodeFactory;
import data.ProgramTrace;

import java.util.List;

public class OriginalStateRecordASTImpl implements  StateRecordAST {

	private NodeFactory nodeFactory;
	private VariableTraceExtractor traceExtractor;

	public OriginalStateRecordASTImpl(NodeFactory nodeFactory, VariableTraceExtractor traceExtractor) {
		this.nodeFactory = nodeFactory;
		this.traceExtractor = traceExtractor;
	}

	@Override
	public ExpressionStatementDelegate getRecordStatement(StateRecord record) {
		return null;
	}

	@Override
	public DeclarationStatementDelegate getInitializationStatement(
			StateRecord record, List<ProgramTrace> traces) {
		return null;
	}
}
