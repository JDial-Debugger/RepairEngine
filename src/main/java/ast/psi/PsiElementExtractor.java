package ast.psi;

import ast.interfaces.Node;
import ast.interfaces.Type;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiType;

public interface PsiElementExtractor {
	<TDelegate extends PsiElement, TWrapper extends Node> TDelegate getDelegateElement(
			Class<TDelegate> delegateType, TWrapper wrapper);
	PsiType getDelegateType(Type type);

}
