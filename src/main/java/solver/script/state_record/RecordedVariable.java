package solver.script.state_record;

import ast.interfaces.PrimitiveType;
import ast.interfaces.Type;

public class RecordedVariable {
	public String name;
	public Type type;

	public RecordedVariable(String name, Type type) {
		this.name = name;
		this.type = type;
	}
}
