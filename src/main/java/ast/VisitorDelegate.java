package ast;

import com.intellij.psi.JavaRecursiveElementVisitor;
import org.jaxen.expr.Expr;

public abstract class VisitorDelegate {
	private JavaRecursiveElementVisitor psiVisitor;

	public void visitMethod(MethodDelegate method) {
		method.acceptChildren(this);
	}

	public void visitExpression(ExpressionDelegate expression) {
		expression.acceptChildren(this);
	}

	public void visitStatement(StatementDelegate statement) {
		statement.acceptChildren(this);
	}

	protected JavaRecursiveElementVisitor getWrappedVisitor() {
		return psiVisitor;
	}
}
