package solver.script.solvable_modification;

import ast.interfaces.ExpressionDelegate;
import ast.interfaces.NodeDelegate;

import java.util.List;

public interface SolvableCodeModificationAST {
	public static final String SOLVER_HOLE_ID = "__HOLE__";
	public List<NodeDelegate> getInitializationCode(SolvableCodeModification modification);
	public ExpressionDelegate getSolvableCode(SolvableCodeModification modification);
}
