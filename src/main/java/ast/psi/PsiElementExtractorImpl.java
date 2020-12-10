package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.TypeDelegate;
import com.intellij.psi.*;

public class PsiElementExtractorImpl implements PsiElementExtractor {

	public PsiElementExtractorImpl() {
	}

	@Override
	public <TWrapped extends PsiElement, TWrapper extends NodeDelegate> TWrapped getWrappedElement(
			Class<TWrapped> wrappedType, TWrapper wrapper) {
		PsiNodeDelegateBase impl = (PsiNodeDelegateBase) wrapper;
		return wrappedType.cast(impl.getWrappedElement());
	}

	@Override
	public PsiType getWrappedType(TypeDelegate typeDelegate) {
		return ((PsiTypeDelegate) typeDelegate).getWrappedType();
	}
}
