package ast.psi;

import ast.interfaces.ExpressionDelegate;
import ast.interfaces.MethodDelegate;
import ast.interfaces.StatementDelegate;
import com.intellij.psi.JavaRecursiveElementVisitor;

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
