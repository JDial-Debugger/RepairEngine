package ast.interfaces;

public interface VisitorDelegate {
	public void visitMethod(MethodDelegate method);

	public void visitExpression(ExpressionDelegate expression);

	public void visitStatement(StatementDelegate statement);

	public void visitBinaryExpression(BinaryExpressionDelegate expression);

	public void visitNode(NodeDelegate node);
}
