package solver.repair;

import ast.interfaces.ExpressionDelegate;
import ast.interfaces.NodeFactory;

public class SolvedModificationsASTImpl<TValue> implements  SolvedModificationsAST{

	private NodeFactory nodeFactory;

	public SolvedModificationsASTImpl(NodeFactory nodeFactory) {
		this.nodeFactory = nodeFactory;
	}

	@Override
	public ExpressionDelegate getSolvedModificationAST(SolvedModification solvedModification) {
		return null;
	}
}
