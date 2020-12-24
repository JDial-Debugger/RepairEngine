package solver.script.constraint;

import ast.interfaces.CodeBlockDelegate;
import ast.interfaces.ExpressionDelegate;
import ast.interfaces.NodeBuilder;
import solver.script.state_record.StateRecord;
import solver.script.state_record.StateRecordAST;

public class SemanticConstraintsASTImpl implements  SemanticConstraintsAST {

	private StateRecordAST stateRecordAST;
	private NodeBuilder nodeBuilder;

	public SemanticConstraintsASTImpl(StateRecordAST stateRecordAST, NodeBuilder nodeBuilder) {
		this.stateRecordAST = stateRecordAST;
		this.nodeBuilder = nodeBuilder;
	}

	@Override
	public void addStateRecordChangedConstraint(
			StateRecord stateRecord, StateRecord originalStateRecord) {
	}

	@Override
	public ExpressionDelegate getReferenceExpression() {
		return null;
	}

	@Override
	public CodeBlockDelegate getInitializationStatements() {
		return null;
	}
}
