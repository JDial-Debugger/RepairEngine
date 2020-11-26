package solver.script.solvable_modification;

import ast.CodeBlockDelegate;
import ast.ExpressionDelegate;

public interface SolvableCodeModificationAST {
	public CodeBlockDelegate getInitializationStatement(SolvableCodeModification modification);
	public ExpressionDelegate getSolvableCode(SolvableCodeModification modification);
}
