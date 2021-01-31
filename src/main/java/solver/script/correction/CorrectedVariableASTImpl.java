package solver.script.correction;

import ast.interfaces.AssertStatement;
import ast.interfaces.DeclarationStatement;
import ast.interfaces.IfStatement;
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
	public DeclarationStatement getInitializationStatement(CorrectedVariable correctedVariable) {
		return null;
	}

	@Override
	public IfStatement getCaptureStatement(CorrectedVariable correctedVariable) {
		return null;
	}

	@Override
	public AssertStatement getValueAssertionStatement(
			CorrectedVariable correctedVariable, Variable<TValue> expectedValue) {
		return null;
	}
}
