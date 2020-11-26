package solver.script.correction;

import ast.AssertStatementDelegate;
import ast.DeclarationStatementDelegate;
import ast.IfStatementDelegate;
import ast.NodeFactory;
import data.Variable;

public class CorrectedVariableASTImpl<TValue> implements CorrectedVariableAST<TValue> {

	private CorrectedVariablesIDGenerator idGenerator;
	private NodeFactory nodeFactory;

	public CorrectedVariableASTImpl(CorrectedVariablesIDGenerator idGenerator, NodeFactory nodeFactory) {
		this.idGenerator = idGenerator;
		this.nodeFactory = nodeFactory;
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
