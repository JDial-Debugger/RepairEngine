package ast.interfaces;

public abstract class VisitorDelegateBase implements VisitorDelegate {

	@Override
	public void visitNode(NodeDelegate node) {
		node.acceptChildren(this);
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
	public void visitUnaryExpression(UnaryExpressionDelegate expression) {
		this.visitExpression(expression);
	}

	@Override
	public void visitAssignExpression(AssignExpressionDelegate expression) {
		this.visitExpression(expression);
	}

	@Override
	public void visitLiteralExpression(LiteralExpressionDelegate expression) {
		this.visitExpression(expression);
	}

	@Override
	public void visitCallExpression(CallExpressionDelegate expression) {
		this.visitExpression(expression);
	}

	@Override
	public void visitLambdaExpression(LambdaExpressionDelegate expression) {
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
