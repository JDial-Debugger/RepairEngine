package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiElement;

import java.util.Set;

/**
 * All AST Nodes extend this class. Serves as a wrapper for a PSI element from the IntelliJ library.
 * Most functionality is delegated to the wrapped PSI element. This class is never meant to be
 * instantiated, but extended for use for the different types of AST nodes.
 */
public class PsiNodeDelegateBase implements NodeDelegate {

	protected PsiElement element;
	protected PsiElementExtractor extractor;
	protected DelegateStore delegateStore;

	protected PsiNodeDelegateBase(NodeConfig<? extends PsiElement> config) {
		this.element = config.delegateElement;
		this.extractor = config.elementExtractor;
		this.delegateStore = config.delegateStore;
	}

	@Override
	public void accept(VisitorDelegate visitor) {
		visitor.visitNode(this);
	}

	@Override
	public void acceptChildren(VisitorDelegate visitor) {
		PsiElement child = element.getFirstChild();
		while (child != null) {
			this.delegateStore.getNodeFrom(child).accept(visitor);
			child = child.getNextSibling();
		}
	}

	@Override
	public Set<String> getVariableNamesInScope() {
		//  TODO Implement this
		return null;
	}

	@Override
	public String toString() {
		return this.element.getText();
	}

	@Override
	public void replace(NodeDelegate nodeToReplaceWith) {
		PsiElement wrappedElement = this.extractor.getDelegateElement(PsiElement.class,
				nodeToReplaceWith);
		this.element.replace(wrappedElement);
		this.element = wrappedElement;
	}

	protected PsiElement getWrappedElement() {
		return this.element;
	}

	@Override
	public NodeDelegate getParent() {
		return this.delegateStore.getNodeFrom(this.element.getParent());
	}

}
