package solver.repair;

import solver.script.solvable_modification.SolvableModificationRegexes;

public interface RawSolverReader {
	public void readSolverOutput(String output, SolvableModificationRegexes regexes);
	public <TValue> SolvedModification<TValue> getSolvedModificationsOfType(Class<TValue> type);
}
