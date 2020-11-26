package solver.script.correction;

import ast.AssertStatementDelegate;
import ast.DeclarationStatementDelegate;
import ast.IfStatementDelegate;
import data.Variable;

public interface CorrectedVariableAST<TValue> {
	public DeclarationStatementDelegate getInitializationStatement(CorrectedVariable correctedVariable);
	public IfStatementDelegate getCaptureStatement(CorrectedVariable correctedVariable);
	public AssertStatementDelegate getValueAssertionStatement(CorrectedVariable correctedVariable, Variable<TValue> expectedValue);
}
