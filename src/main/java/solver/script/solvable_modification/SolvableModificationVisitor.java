package solver.script.solvable_modification;

import java.util.Set;

public class SolvableModificationVisitor extends PsiVisitorDelegate {

	private Set<SolvableCodeModification> solvableModifications;
	public SolvableModificationVisitor(Set<SolvableCodeModification> solvableModifications) {
		this.solvableModifications = solvableModifications;
	}


}
