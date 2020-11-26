package solver.repair;

import solver.script.solvable_modification.SolvableCodeModification;

import java.util.Enumeration;
import java.util.Set;

public interface SolvedModifications<TValue> extends Enumeration<SolvedModification<TValue>> {
	public void addSolvableModifications(Set<SolvableCodeModification> solvableModifications);
	public void solveChangedValueModification(String id, TValue value);
}
