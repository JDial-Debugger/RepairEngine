package solver.script.solvable_modification;

import ast.NodeDelegate;

import java.util.List;

public interface SolvableCodeModificationSetupAggregator {
	public void addSetup(SolvableCodeModification modification);
	public List<NodeDelegate> getAllSetups();
}
