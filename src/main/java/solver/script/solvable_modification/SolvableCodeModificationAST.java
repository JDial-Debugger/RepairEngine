package solver.script.solvable_modification;

import ast.CodeBlockDelegate;
import ast.ExpressionDelegate;
import ast.NodeDelegate;

import java.util.List;

public interface SolvableCodeModificationAST {
	public static final String SOLVER_HOLE_ID = "__HOLE__";
	public List<NodeDelegate> getInitializationCode(SolvableCodeModification modification);
	public ExpressionDelegate getSolvableCode(SolvableCodeModification modification);
}
