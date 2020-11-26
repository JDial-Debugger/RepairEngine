package solver.repair;

import ast.ExpressionDelegate;

public interface SolvedModificationsAST<TValue> {
	public ExpressionDelegate getSolvedModificationAST(SolvedModification<TValue> solvedModification);
}
