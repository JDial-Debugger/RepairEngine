package ast;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

public abstract class PsiNodeDelegate implements NodeDelegate {

	protected PsiElement element;

	protected PsiNodeDelegate(PsiElement element) {
		this.element = element;
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
}
