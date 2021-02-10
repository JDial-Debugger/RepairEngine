package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.TypeDelegate;
import com.intellij.psi.*;

public class PsiElementExtractorImpl implements PsiElementExtractor {

	public PsiElementExtractorImpl() {
	}

	@Override
	public <TWrapped extends PsiElement, TWrapper extends NodeDelegate> TWrapped getDelegateElement(
			Class<TWrapped> delegateType, TWrapper wrapper) {
		PsiNodeDelegateBase impl = (PsiNodeDelegateBase) wrapper;
		return delegateType.cast(impl.getWrappedElement());
	}

	@Override
	public PsiType getDelegateType(TypeDelegate typeDelegate) {
		return ((PsiTypeDelegate) typeDelegate).getWrappedType();
	}
}
