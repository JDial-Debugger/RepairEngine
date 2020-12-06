package ast.interfaces;

public abstract class VisitorDelegateBase implements VisitorDelegate {

	@Override
	public void visitNode(NodeDelegate node) {
	}

	@Override
	public void visitMethod(MethodDelegate method) {
		this.visitNode(method);
	}

	@Override
	public void visitBinaryExpression(BinaryExpressionDelegate expression) {
		this.visitExpression(expression);
	}

	@Override
	public void visitExpression(ExpressionDelegate expression) {
		this.visitNode(expression);
	}

	@Override
	public void visitStatement(StatementDelegate statement) {
		this.visitNode(statement);
	}
}
