package ast.psi;

import ast.interfaces.*;
import com.intellij.psi.*;

//  Used when classes outside the factory class in this package need to create delegate class for a Psi element
public interface NodeFactory {

	AssertStatement getNode(PsiAssertStatement delegate);

	AssignExpression getNode(PsiAssignmentExpression delegate);

	BinaryExpression getNode(PsiBinaryExpression delegate);

	CodeBlock getNode(PsiCodeBlock delegate);

	DeclarationStatement getNode(PsiDeclarationStatement delegate);

	Expression getNode(PsiExpression delegate);

	ExpressionStatement getNode(PsiExpressionStatement delegate);

	File getNode(PsiFile delegate);

	IfStatement getNode(PsiIfStatement delegate);

	LiteralExpression getNode(PsiLiteralExpression delegate);

	LocalVariable getNode(PsiLocalVariable delegate);

	Method getNode(PsiMethod delegate);

	Node getNode(PsiElement delegate);

	Parameter getNode(PsiParameter delegate);

	ParameterList getNode(PsiParameterList delegate);

	ReturnStatement getNode(PsiReturnStatement delegate);

	Statement getNode(PsiStatement delegate);

	BlockStatement getNode(PsiBlockStatement delegate);

	ForStatement getNode(PsiForStatement delegate);

	UnaryExpression getNode(PsiUnaryExpression delegate);

	ArrayAccessExpression getNode(PsiArrayAccessExpression delegate);

	Type getType(PsiType delegate);

}
