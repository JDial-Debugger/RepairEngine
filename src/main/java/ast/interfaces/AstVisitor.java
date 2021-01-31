package ast.interfaces;

public interface AstVisitor {
	public void visitMethod(Method method);

	public void visitExpression(Expression expression);

	public void visitStatement(Statement statement);

	public void visitBinaryExpression(BinaryExpression expression);

	public void visitNode(Node node);
}
