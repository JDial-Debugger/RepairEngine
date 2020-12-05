package ast;

import com.intellij.psi.*;

import java.util.HashMap;
import java.util.Map;

class PsiElementExtractorImpl implements PsiElementExtractor {

	public PsiElementExtractorImpl() {
	}

	@Override
	public <TWrapped extends PsiElement, TWrapper extends NodeDelegate> TWrapped getWrappedElement(
			Class<TWrapped> wrappedType, TWrapper wrapper) {
		PsiNodeDelegate impl = (PsiNodeDelegate) wrapper;
		return wrappedType.cast(impl.getWrappedElement());
	}

	@Override
	public PsiType getWrappedType(TypeDelegate typeDelegate) {
		return ((PsiTypeDelegate) typeDelegate).getWrappedType();
	}
}
