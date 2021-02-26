package solver.script.constraint;

import ast.interfaces.Statement;
import solver.script.solvable_modification.SolvableModificationId;

import java.util.List;

public interface SyntacticConstraintsAST extends MinimizableConstraintAST {
	List<Statement> getInitializationStatements(List<SolvableModificationId> modificationIds);
}
