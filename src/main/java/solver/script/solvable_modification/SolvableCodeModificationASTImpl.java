package solver.script.solvable_modification;

import ast.CodeBlockDelegate;
import ast.ExpressionDelegate;

public class SolvableCodeModificationASTImpl implements SolvableCodeModificationAST {

	private SolvableModificationIDGenerator idGenerator;
	private SolvableCodeModificationAST ast;

	public SolvableCodeModificationASTImpl(
			SolvableModificationIDGenerator idGenerator, SolvableCodeModificationAST ast) {
		this.idGenerator = idGenerator;
		this.ast = ast;
	}

	@Override
	public CodeBlockDelegate getInitializationStatement(SolvableCodeModification modification) {
		return null;
	}

	@Override
	public ExpressionDelegate getSolvableCode(SolvableCodeModification modification) {
		return null;
	}
}
