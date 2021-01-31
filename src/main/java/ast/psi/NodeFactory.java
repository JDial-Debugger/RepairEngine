package ast.psi;

import ast.interfaces.*;
import com.intellij.psi.*;

//  Used when classes outside the factory class in this package need to create delegate class for a Psi element
public interface NodeFactory {

	AssertStatement getNode(AssertStatementImpl delegate);

	AssignExpression getNode(PsiAssignmentExpression delegate);

	BinaryExpression getNode(BinaryExpressionImpl delegate);

	CodeBlock getNode(CodeBlockImpl delegate);

	DeclarationStatement getNode(DeclarationStatementImpl delegate);

	Expression getNode(ExpressionImpl delegate);

	ExpressionStatement getNode(ExpressionStatementImpl delegate);

	File getNode(FileImpl delegate);

	IfStatement getNode(IfStatementImpl delegate);

	LiteralExpression getNode(LiteralExpressionImpl delegate);

	Method getNode(MethodImpl delegate);

	Node getNode(PsiElement delegate);

	ParameterList getNode(ParameterListImpl delegate);

	ReturnStatement getNode(ReturnStatementImpl delegate);

	Statement getNode(StatementImpl delegate);

	Type getType(PsiType delegate);
}
