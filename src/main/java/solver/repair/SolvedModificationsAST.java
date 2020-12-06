package solver.repair;

import ast.interfaces.ExpressionDelegate;

public interface SolvedModificationsAST<TValue> {
	public ExpressionDelegate getSolvedModificationAST(SolvedModification<TValue> solvedModification);
}
