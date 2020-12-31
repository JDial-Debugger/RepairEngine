package ast.psi;

import ast.interfaces.*;
import com.intellij.psi.*;
import kotlin.contracts.Returns;

//  Used when classes outside the factory class in this package need to create delegate class for a Psi element
interface NodeFactory {

	AssertStatementDelegate getNode(PsiAssertStatement delegate);

	AssignExpressionDelegate getNode(PsiAssignmentExpression delegate);

	BinaryExpressionDelegate getNode(PsiBinaryExpression delegate);

	CodeBlockDelegate getNode(PsiCodeBlock delegate);

	DeclarationStatementDelegate getNode(PsiDeclarationStatement delegate);

	ExpressionDelegate getNode(PsiExpression delegate);

	ExpressionStatementDelegate getNode(PsiExpressionStatement delegate);

	FileDelegate getNode(PsiFile delegate);

	IfStatementDelegate getNode(PsiIfStatement delegate);

	LiteralExpressionDelegate getNode(PsiLiteralExpression delegate);

	MethodDelegate getNode(PsiMethod delegate);

	NodeDelegate getNode(PsiElement delegate);

	ParameterListDelegate getNode(PsiParameterList delegate);

	ReturnStatementDelegate getNode(PsiReturnStatement delegate);

	StatementDelegate getNode(PsiStatement delegate);

	TypeDelegate getType(PsiType delegate);
}
