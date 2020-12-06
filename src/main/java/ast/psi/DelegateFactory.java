package ast.psi;

import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;

//  Used when classes outside the factory class in this package need to create delegate class for a Psi element
interface DelegateFactory {
	NodeDelegate getNode(PsiElement wrappedElement);
}
