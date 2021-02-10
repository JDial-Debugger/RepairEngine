package ast.interfaces;


import ast.psi.PsiAssignExpressionDelegate;
import ast.psi.PsiExpressionDelegate;

import java.util.List;

public interface NodeBuilder {

	LiteralExpressionDelegate buildLiteralIntExpression(int expressionContents);

	DeclarationStatementDelegate buildEmptyArrayDeclaration(
			TypeDelegate type, String name, Integer[] dimensions);

	LiteralExpressionDelegate buildDefaultLiteralExpressionFor(TypeDelegate type);

	AssignExpressionDelegate buildAssignExpression(ExpressionDelegate lhs, ExpressionDelegate rhs);

	StatementDelegate buildReturnStatement(ExpressionDelegate expressionToReturn);

	CodeBlockDelegate buildCodeBlockFromStatements(List<StatementDelegate> statements);

	CodeBlockDelegate buildEmptyCodeBlock();

	ExpressionDelegate buildExpressionFromText(String text);

	StatementDelegate buildStatementFromText(String text);

	DeclarationStatementDelegate buildEmptyDeclarationStatement();

	DeclarationStatementDelegate buildDeclarationStatement(
			String name, TypeDelegate type, ExpressionDelegate initializer);

	IfStatementDelegate buildIfStatement(
			ExpressionDelegate condition, CodeBlockDelegate thenBody);

	IfStatementDelegate buildIfStatement(
			ExpressionDelegate condition, CodeBlockDelegate thenBody, CodeBlockDelegate elseBody);

	MethodDelegate buildMethod(
			TypeDelegate returnType,
			String name,
			ParameterListDelegate paramList,
			CodeBlockDelegate body);

	BinaryExpressionDelegate buildBinaryExpression(
			ExpressionDelegate left, BinaryOperator op, ExpressionDelegate right);

	ExpressionDelegate buildMethodCall(String methodName, ExpressionDelegate... params);
}
