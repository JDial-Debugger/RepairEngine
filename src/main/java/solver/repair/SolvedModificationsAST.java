package solver.repair;

import ast.interfaces.Expression;

public interface SolvedModificationsAST<TValue> {
	public Expression getSolvedModificationAST(SolvedModification<TValue> solvedModification);
}
