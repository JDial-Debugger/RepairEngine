package ast.psi;

import ast.interfaces.Node;
import ast.interfaces.Type;
import com.intellij.psi.*;

public class PsiElementExtractorImpl implements PsiElementExtractor {

	public PsiElementExtractorImpl() {
	}

	@Override
	public <TWrapped extends PsiElement, TWrapper extends Node> TWrapped getDelegateElement(
			Class<TWrapped> delegateType, TWrapper wrapper) {
		PsiNodeBase impl = (PsiNodeBase) wrapper;
		return delegateType.cast(impl.getWrappedElement());
	}

	@Override
	public com.intellij.psi.PsiType getDelegateType(Type type) {
		return ((PsiType) type).getWrappedType();
	}
}
