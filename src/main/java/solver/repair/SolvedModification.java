package solver.repair;

import solver.script.solvable_modification.SolvableCodeModification;

import java.util.Optional;

public class SolvedModification<TValue> {
	public SolvableCodeModification solvableModification;
	public Optional<TValue> solvedValue;
}
