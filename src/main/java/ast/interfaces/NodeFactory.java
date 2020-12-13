package ast.interfaces;


import java.util.List;

public interface NodeFactory {

	LiteralExpressionDelegate getLiteralIntExpression(int expressionContents);

	ArrayDeclarationStatementDelegate getEmptyArrayDeclaration(
			TypeDelegate type, String name, Integer[] dimensions);

	LiteralExpressionDelegate getDefaultLiteralExpressionFor(TypeDelegate type);

	StatementDelegate getReturnStatement(ExpressionDelegate expressionToReturn);

	CodeBlockDelegate getCodeBlockFromStatements(List<StatementDelegate> statements);

	CodeBlockDelegate getEmptyCodeBlock();

	public ExpressionDelegate getExpressionFromText(String text);

	public StatementDelegate getStatementFromText(String text);

	public DeclarationStatementDelegate getEmptyDeclarationStatement();

	DeclarationStatementDelegate getDeclarationStatement(
			String name, TypeDelegate type, ExpressionDelegate initializer);

	public IfStatementDelegate getIfStatement(
			ExpressionDelegate condition,
			CodeBlockDelegate thenBody);

	public IfStatementDelegate getIfStatement(
			ExpressionDelegate condition,
			CodeBlockDelegate thenBody,
			CodeBlockDelegate elseBody);

	MethodDelegate getMethod(
			TypeDelegate returnType,
			String name,
			ParameterListDelegate paramList,
			CodeBlockDelegate body);

	public MethodDelegate getEmptyMethod();

	BinaryExpressionDelegate getBinaryExpression(
			ExpressionDelegate left, BinaryOperator op, ExpressionDelegate right);

	ExpressionDelegate getMethodCall(String methodName, ExpressionDelegate... params);
}
