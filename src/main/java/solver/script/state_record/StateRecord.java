package solver.script.state_record;

import ast.interfaces.Type;
import ast.interfaces.TypeDelegate;

import java.util.Objects;

public class StateRecord {
	public String variableName;
	public String functionName;
	public TypeDelegate variableType;

	public StateRecord() {}

	public StateRecord(String variableName, String functionName, TypeDelegate variableType) {
		this.variableName = variableName;
		this.functionName = functionName;
		this.variableType = variableType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		StateRecord that = (StateRecord) o;
		return Objects.equals(variableName, that.variableName) && Objects.equals(functionName,
				that.functionName) && Objects.equals(variableType, that.variableType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(variableName, functionName, variableType);
	}
}
