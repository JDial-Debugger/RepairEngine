package solver.repair;

import solver.script.solvable_modification.SolvableModificationRegexes;

public class RawSolverReaderImpl implements  RawSolverReader {

	@Override
	public void readSolverOutput(
			String output, SolvableModificationRegexes regexes) {

	}

	@Override
	public <TValue> SolvedModification<TValue> getSolvedModificationsOfType(Class<TValue> type) {
		return null;
	}
}
