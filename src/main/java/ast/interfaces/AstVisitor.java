package ast.interfaces;

public interface AstVisitor {
	public void visitMethod(Method method);

	public void visitExpression(Expression expression);

	public void visitStatement(Statement statement);

	public void visitBinaryExpression(BinaryExpression expression);

	public void visitNode(Node node);

	void visitCodeBlock(CodeBlock codeBlock);

	void visitAssignExpression(AssignExpression expr);

	void visitBlockStatement(BlockStatement stmt);

	void visitDeclarationStatement(DeclarationStatement stmt);

	void visitExpressionStatement(ExpressionStatement stmt);

	void visitIfStatement(IfStatement stmt);

	void visitLiteralExpression(LiteralExpression expr);

	void visitLocalVariable(LocalVariable var);

	void visitParameter(Parameter param);

	void visitParameterList(ParameterList paramList);

	void visitReturnStatement(ReturnStatement stmt);

	void visitForStatement(ForStatement stmt);

	void visitUnaryExpression(UnaryExpression expr);
}
