package solver.script.solvable_modification;

import ast.interfaces.Expression;
import ast.interfaces.Node;

import java.util.List;

public interface SolvableCodeModificationAST {
	public static final String SOLVER_HOLE_ID = "__HOLE__";
	public List<Node> getInitializationCode(SolvableCodeModification modification);
	public Expression getSolvableCode(SolvableCodeModification modification);
}
