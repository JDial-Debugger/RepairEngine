package solver.script.solvable_modification;

import java.util.regex.Pattern;

public class SolvableModificationRegexes {
	public Pattern funcName;
	public Pattern changeVariable;
	public SolvableModificationRegexes(Pattern funcName, Pattern changeVariable) {
		this.funcName = funcName;
		this.changeVariable = changeVariable;
	}
}
