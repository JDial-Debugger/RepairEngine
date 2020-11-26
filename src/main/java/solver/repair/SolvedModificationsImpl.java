package solver.repair;

import java.util.Map;
import java.util.Set;

public class SolvedModificationsImpl<TValue> implements  SolvedModifications {

	private Map<String, SolvedModification<TValue>> idToSolvedModification;

	public SolvedModificationsImpl(Map<String, SolvedModification<TValue>> idToSolvedModification) {
		this.idToSolvedModification = idToSolvedModification;
	}

	@Override
	public void addSolvableModifications(Set solvableModifications) {

	}

	@Override
	public void solveChangedValueModification(String id, Object o) {

	}

	@Override
	public boolean hasMoreElements() {
		return false;
	}

	@Override
	public Object nextElement() {
		return null;
	}
}
