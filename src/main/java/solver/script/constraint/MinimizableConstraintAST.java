package solver.script.constraint;

import ast.CodeBlockDelegate;
import ast.ExpressionDelegate;

public interface MinimizableConstraintAST {
	public ExpressionDelegate getReferenceExpression();
	public CodeBlockDelegate getInitializationStatements();
}
