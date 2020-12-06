package solver.script.correction;

import ast.interfaces.AssertStatementDelegate;
import ast.interfaces.DeclarationStatementDelegate;
import ast.interfaces.IfStatementDelegate;
import data.Variable;

public interface CorrectedVariableAST<TValue> {
	public DeclarationStatementDelegate getInitializationStatement(CorrectedVariable correctedVariable);
	public IfStatementDelegate getCaptureStatement(CorrectedVariable correctedVariable);
	public AssertStatementDelegate getValueAssertionStatement(CorrectedVariable correctedVariable, Variable<TValue> expectedValue);
}
