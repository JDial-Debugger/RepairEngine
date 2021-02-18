package solver.script.constraint;

import ast.interfaces.Expression;
import ast.interfaces.NodeBuilder;
import ast.interfaces.Statement;
import solver.script.state_record.StateRecord;
import solver.script.state_record.StateRecordAST;

import java.util.List;

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
	public Expression getReferenceExpression() {
		return null;
	}

	@Override
	public List<Statement> getInitializationStatements() {
		return null;
	}
}
