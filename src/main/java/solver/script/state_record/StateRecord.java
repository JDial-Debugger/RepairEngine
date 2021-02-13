package solver.script.state_record;

import ast.interfaces.Type;

import java.util.Objects;

public class StateRecord {
	//  TODO possible optimization: use expression instead of string avoids needless building of expression
	public String variableName;
	public String functionName;
	public Type variableType;

	public StateRecord() {}

	public StateRecord(String variableName, String functionName, Type variableType) {
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
