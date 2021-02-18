package solver.script.constraint;

import ast.interfaces.Expression;
import ast.interfaces.Statement;

import java.util.List;

public interface MinimizableConstraintAST {
	Expression getReferenceExpression();
	List<Statement> getInitializationStatements();
}
