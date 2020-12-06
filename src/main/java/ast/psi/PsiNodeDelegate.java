package ast.psi;

import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;

public abstract class PsiNodeDelegate implements NodeDelegate {

	protected PsiElement element;
	protected PsiElementExtractor extractor;

	protected PsiNodeDelegate(PsiElement element) {
		this.element = element;
	}

	public void setImplementationExtractor(PsiElementExtractor extractor) {
		this.extractor = extractor;
	}

	@Override
	public void accept(VisitorDelegate visitor) {
		element.accept(visitor.getWrappedVisitor());
	}

	@Override
	public void acceptChildren(VisitorDelegate visitor) {
		element.acceptChildren(visitor.getWrappedVisitor());
	}

	@Override
	public String toString() {
		return element.getText();
	}

	protected PsiElement getWrappedElement() {
		return element;
	}

}
