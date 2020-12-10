package solver.script.solvable_modification;

import ast.interfaces.ExpressionDelegate;

import java.util.Objects;

public class SolvableCodeModification {
	public ExpressionDelegate originalCode;
	public SolvableModificationId id;

	public SolvableCodeModification(ExpressionDelegate originalCode, SolvableModificationId id) {
		this.originalCode = originalCode;
		this.id = id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SolvableCodeModification that = (SolvableCodeModification) o;
		return Objects.equals(originalCode, that.originalCode) && Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(originalCode, id);
	}
}
