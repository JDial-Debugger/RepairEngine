package ast.psi;

import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiStatement;

//  Used when classes outside the factory class in this package need to create delegate class for a Psi element
interface DelegateFactory {
	NodeDelegate getNode(PsiElement wrappedElement);

	NodeDelegate getNode(PsiExpression wrappedExpression);

	NodeDelegate getNode(PsiStatement wrappedStatement);

	NodeDelegate getNode(PsiBinaryExpression wrappedExpression);
}
