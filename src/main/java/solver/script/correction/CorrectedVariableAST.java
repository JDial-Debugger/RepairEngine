package solver.script.correction;

import ast.interfaces.AssertStatement;
import ast.interfaces.DeclarationStatement;
import ast.interfaces.IfStatement;
import data.Variable;

public interface CorrectedVariableAST<TValue> {
	public DeclarationStatement getInitializationStatement(CorrectedVariable correctedVariable);
	public IfStatement getCaptureStatement(CorrectedVariable correctedVariable);
	public AssertStatement getValueAssertionStatement(CorrectedVariable correctedVariable, Variable<TValue> expectedValue);
}
