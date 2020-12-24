package solver.repair;

import ast.interfaces.ExpressionDelegate;
import ast.interfaces.NodeBuilder;

public class SolvedModificationsASTImpl<TValue> implements  SolvedModificationsAST{

	private NodeBuilder nodeBuilder;

	public SolvedModificationsASTImpl(NodeBuilder nodeBuilder) {
		this.nodeBuilder = nodeBuilder;
	}

	@Override
	public ExpressionDelegate getSolvedModificationAST(SolvedModification solvedModification) {
		return null;
	}
}
