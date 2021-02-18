package ast.interfaces;


import java.util.List;

public interface NodeBuilder {

	LiteralExpression buildLiteralIntExpression(int expressionContents);

	DeclarationStatement buildEmptyArrayDeclaration(
			Type type, String name, Integer[] dimensions);

	LiteralExpression buildDefaultLiteralExpressionFor(Type type);

	Statement buildReturnStatement(Expression expressionToReturn);

	CodeBlock buildCodeBlockFromStatements(List<Statement> statements);

	CodeBlock buildEmptyCodeBlock();

	Expression buildExpressionFromText(String text);

	Statement buildStatementFromText(String text);

	DeclarationStatement buildEmptyDeclarationStatement();

	DeclarationStatement buildDeclarationStatement(
			String name, Type type, Expression initializer);

	IfStatement buildIfStatement(
			Expression condition, CodeBlock thenBody);

	IfStatement buildIfStatement(
			Expression condition, CodeBlock thenBody, CodeBlock elseBody);

	Method buildMethod(
			Type returnType,
			String name,
			ParameterList paramList,
			CodeBlock body);

	BinaryExpression buildBinaryExpression(
			Expression left, BinaryOperator op, Expression right);

	Expression buildMethodCall(String methodName, Expression... params);

	AssignExpression buildAssignExpression(Expression left, Expression right);

	AssignExpression buildAssignExpression(
			Expression left, AssignOperator op, Expression right);

	ExpressionStatement buildExpressionStatement(Expression expression);

	ForStatement buildForStatement(
			Statement init,
			Expression condition,
			Statement update,
			Statement body);
}
