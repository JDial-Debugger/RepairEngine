package ast.interfaces;


public interface VisitorDelegate {
	void visitMethod(MethodDelegate method);

	void visitExpression(ExpressionDelegate expression);

	void visitStatement(StatementDelegate statement);

	void visitBinaryExpression(BinaryExpressionDelegate expression);

	void visitUnaryExpression(UnaryExpressionDelegate expression);

	void visitAssignExpression(AssignExpressionDelegate expression);

	void visitLiteralExpression(LiteralExpressionDelegate expression);

	void visitCallExpression(CallExpressionDelegate expression);

	void visitLambdaExpression(LambdaExpressionDelegate expression);

	void visitNode(NodeDelegate node);
}
