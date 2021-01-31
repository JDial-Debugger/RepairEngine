package solver.repair;

import ast.interfaces.Expression;
import ast.interfaces.NodeBuilder;

public class SolvedModificationsASTImpl<TValue> implements  SolvedModificationsAST{

	private NodeBuilder nodeBuilder;

	public SolvedModificationsASTImpl(NodeBuilder nodeBuilder) {
		this.nodeBuilder = nodeBuilder;
	}

	@Override
	public Expression getSolvedModificationAST(SolvedModification solvedModification) {
		return null;
	}
}
