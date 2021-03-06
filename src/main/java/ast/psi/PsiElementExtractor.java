package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.TypeDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiType;

interface PsiElementExtractor {
	<TWrapped extends PsiElement, TWrapper extends NodeDelegate> TWrapped getWrappedElement(
			Class<TWrapped> wrappedType, TWrapper wrapper);
	PsiType getWrappedType(TypeDelegate typeDelegate);

}
