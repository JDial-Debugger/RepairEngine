package solver.script.state_record;

import ast.DeclarationStatementDelegate;
import ast.ExpressionStatementDelegate;
import ast.NodeFactory;
import data.ProgramTrace;

import java.util.List;

public class StateRecordASTImpl implements  StateRecordAST{

	private NodeFactory nodeFactory;
	private String stateIndexId;

	public StateRecordASTImpl(NodeFactory nodeFactory, String stateIndexId) {
		this.nodeFactory = nodeFactory;
		this.stateIndexId = stateIndexId;
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
