package solver.script.state_record;

import ast.interfaces.Type;
import ast.interfaces.TypeDelegate;

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
}
