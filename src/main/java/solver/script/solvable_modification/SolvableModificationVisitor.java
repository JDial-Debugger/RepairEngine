package solver.script.solvable_modification;

import ast.psi.VisitorDelegate;

import java.util.Set;

public class SolvableModificationVisitor extends VisitorDelegate {

	private Set<SolvableCodeModification> solvableModifications;
	public SolvableModificationVisitor(Set<SolvableCodeModification> solvableModifications) {
		this.solvableModifications = solvableModifications;
	}
}
