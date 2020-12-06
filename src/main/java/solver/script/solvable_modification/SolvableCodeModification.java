package solver.script.solvable_modification;

import ast.interfaces.ExpressionDelegate;

public class SolvableCodeModification {
	public ExpressionDelegate originalCode;
	public SolvableModificationIds id;

	public SolvableCodeModification(ExpressionDelegate originalCode, SolvableModificationIds id) {
		this.originalCode = originalCode;
		this.id = id;
	}
}
