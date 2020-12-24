package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.TypeDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiType;

public interface PsiElementExtractor {
	<TDelegate extends PsiElement, TWrapper extends NodeDelegate> TDelegate getDelegateElement(
			Class<TDelegate> delegateType, TWrapper wrapper);
	PsiType getDelegateType(TypeDelegate typeDelegate);

}
