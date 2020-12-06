package solver.script.constraint;

import ast.interfaces.CodeBlockDelegate;
import ast.interfaces.ExpressionDelegate;
import ast.interfaces.NodeFactory;
import solver.script.state_record.StateRecord;
import solver.script.state_record.StateRecordAST;

public class SemanticConstraintsASTImpl implements  SemanticConstraintsAST {

	private StateRecordAST stateRecordAST;
	private NodeFactory nodeFactory;

	public SemanticConstraintsASTImpl(StateRecordAST stateRecordAST, NodeFactory nodeFactory) {
		this.stateRecordAST = stateRecordAST;
		this.nodeFactory = nodeFactory;
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
