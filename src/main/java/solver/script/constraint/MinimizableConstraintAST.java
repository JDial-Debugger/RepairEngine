package solver.script.constraint;

import ast.interfaces.CodeBlockDelegate;
import ast.interfaces.ExpressionDelegate;

public interface MinimizableConstraintAST {
	public ExpressionDelegate getReferenceExpression();
	public CodeBlockDelegate getInitializationStatements();
}
