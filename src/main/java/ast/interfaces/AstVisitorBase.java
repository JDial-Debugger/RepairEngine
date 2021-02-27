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

	@Override
	public void visitCodeBlock(CodeBlock codeBlock) {
		this.visitNode(codeBlock);
	}

	@Override
	public void visitAssignExpression(AssignExpression expr) {
		this.visitExpression(expr);
	}

	@Override
	public void visitBlockStatement(BlockStatement stmt) {
		this.visitStatement(stmt);
	}

	@Override
	public void visitDeclarationStatement(DeclarationStatement stmt) {
		this.visitStatement(stmt);
	}

	@Override
	public void visitExpressionStatement(ExpressionStatement stmt) {
		this.visitStatement(stmt);
	}

	@Override
	public void visitIfStatement(IfStatement stmt) {
		this.visitStatement(stmt);
	}

	@Override
	public void visitLiteralExpression(LiteralExpression expr) {
		this.visitExpression(expr);
	}

	@Override
	public void visitLocalVariable(LocalVariable var) {
		this.visitNode(var);
	}

	@Override
	public void visitParameter(Parameter param) {
		this.visitNode(param);
	}

	@Override
	public void visitParameterList(ParameterList paramList) {
		this.visitNode(paramList);
	}

	@Override
	public void visitReturnStatement(ReturnStatement stmt) {
		this.visitStatement(stmt);
	}

	@Override
	public void visitForStatement(ForStatement stmt) {
		this.visitStatement(stmt);
	}

	@Override
	public void visitArrayAccessExpression(ArrayAccessExpression expr) {
		this.visitExpression(expr);
	}

	@Override
	public void visitUnaryExpression(UnaryExpression expr) {
		this.visitExpression(expr);
	}
}
