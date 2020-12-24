package solver.script.correction;

import ast.interfaces.AssertStatementDelegate;
import ast.interfaces.DeclarationStatementDelegate;
import ast.interfaces.IfStatementDelegate;
import ast.interfaces.NodeBuilder;
import data.Variable;

public class CorrectedVariableASTImpl<TValue> implements CorrectedVariableAST<TValue> {

	private CorrectedVariablesIDGenerator idGenerator;
	private NodeBuilder nodeBuilder;

	public CorrectedVariableASTImpl(CorrectedVariablesIDGenerator idGenerator, NodeBuilder nodeBuilder) {
		this.idGenerator = idGenerator;
		this.nodeBuilder = nodeBuilder;
	}

	@Override
	public DeclarationStatementDelegate getInitializationStatement(CorrectedVariable correctedVariable) {
		return null;
	}

	@Override
	public IfStatementDelegate getCaptureStatement(CorrectedVariable correctedVariable) {
		return null;
	}

	@Override
	public AssertStatementDelegate getValueAssertionStatement(
			CorrectedVariable correctedVariable, Variable<TValue> expectedValue) {
		return null;
	}
}
