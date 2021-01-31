package ast.interfaces;

public abstract class AstVisitorBase implements AstVisitor {

	@Override
	public void visitNode(Node node) {
		node.acceptChildren(this);
	}

	@Override
	public void visitMethod(Method method) {
		this.visitNode(method);
	}

	@Override
	public void visitBinaryExpression(BinaryExpression expression) {
		this.visitExpression(expression);
	}

	@Override
	public void visitExpression(Expression expression) {
		this.visitNode(expression);
	}

	@Override
	public void visitStatement(Statement statement) {
		this.visitNode(statement);
	}
}
