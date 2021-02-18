package solver.script.constraint;

import solver.script.solvable_modification.SolvableModificationId;

public interface SyntacticConstraintsAST extends MinimizableConstraintAST {
	void addSolvableModificationConstraint(SolvableModificationId solvableModificationId);
}
