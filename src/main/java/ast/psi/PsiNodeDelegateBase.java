package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiElement;

import java.util.Map;

public class PsiNodeDelegateBase implements NodeDelegate {

	protected PsiElement element;
	protected PsiElementExtractor extractor;
	protected DelegateFactory delegateFactory;
	protected Map<PsiElement, NodeDelegate> wrappedElementToDelegate;

	protected PsiNodeDelegateBase(PsiElement element) {
		this.element = element;
	}

	public void setImplementationExtractor(PsiElementExtractor extractor) {
		this.extractor = extractor;
	}

	@Override
	public void accept(VisitorDelegate visitor) {
		visitor.visitNode(this);
	}

	@Override
	public void acceptChildren(VisitorDelegate visitor) {
		PsiElement child = element.getFirstChild();
		while (child != null) {
			this.getDelegateFrom(child).accept(visitor);
			child = child.getNextSibling();
		}
	}

	protected NodeDelegate getDelegateFrom(PsiElement wrappedElement) {
		if (this.wrappedElementToDelegate.containsKey(wrappedElement)) {
			return this.wrappedElementToDelegate.get(wrappedElement);
		}
		NodeDelegate wrapper = delegateFactory.getNode(wrappedElement);
		this.wrappedElementToDelegate.put(wrappedElement, wrapper);
		return wrapper;
	}

	@Override
	public String toString() {
		return this.element.getText();
	}

	@Override
	public void replace(NodeDelegate nodeToReplaceWith) {
		PsiElement wrappedElement = this.extractor.getWrappedElement(
				PsiElement.class,
				nodeToReplaceWith);
		this.element.replace(wrappedElement);
		this.element = wrappedElement;
	}

	protected PsiElement getWrappedElement() {
		return this.element;
	}

	@Override
	public NodeDelegate getParent() {
		return this.getDelegateFrom(this.element.getParent());
	}

}
