package solver.script.constraint;

import ast.interfaces.CodeBlock;
import ast.interfaces.Expression;

public interface MinimizableConstraintAST {
	public Expression getReferenceExpression();
	public CodeBlock getInitializationStatements();
}
