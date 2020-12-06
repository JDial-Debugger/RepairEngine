package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiElement;

import java.util.Map;

public class PsiNodeDelegateBase implements NodeDelegate {

	protected PsiElement element;
	protected PsiElementExtractor extractor;
	protected DelegateFactory delegateFactory;
	protected Map<PsiElement, NodeDelegate> wrappedElementToChild;

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
			this.getChildFrom(child).accept(visitor);
			child = child.getNextSibling();
		}
	}

	protected NodeDelegate getChildFrom(PsiElement wrappedChild) {
		if (this.wrappedElementToChild.containsKey(wrappedChild)) {
			return this.wrappedElementToChild.get(wrappedChild);
		}
		NodeDelegate wrapper = delegateFactory.getNode(wrappedChild);
		this.wrappedElementToChild.put(wrappedChild, wrapper);
		return wrapper;
	}

	@Override
	public String toString() {
		return element.getText();
	}

	@Override
	public void replace(NodeDelegate nodeToReplaceWith) {
		PsiElement wrappedElement = this.extractor.getWrappedElement(PsiElement.class, nodeToReplaceWith);
		this.element.replace(wrappedElement);
		this.element = wrappedElement;
	}

	protected PsiElement getWrappedElement() {
		return element;
	}

}
