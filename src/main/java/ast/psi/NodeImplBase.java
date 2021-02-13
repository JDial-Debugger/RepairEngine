package ast.psi;

import ast.interfaces.Node;
import ast.interfaces.AstVisitor;
import ast.interfaces.NodeBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;

import java.util.Set;

/**
 * All AST Nodes extend this class. Serves as a wrapper for a PSI element from the IntelliJ library.
 * Most functionality is delegated to the wrapped PSI element. This class is never meant to be
 * instantiated, but extended for use for the different types of AST nodes.
 */
public class NodeImplBase implements Node {

	protected PsiElement element;
	protected PsiElementExtractor extractor;
	protected DelegateStore delegateStore;
	protected NodeBuilder nodeBuilder;

	protected NodeImplBase(NodeConfig<? extends PsiElement> config) {
		this.element = config.delegateElement;
		this.extractor = config.elementExtractor;
		this.delegateStore = config.delegateStore;
		this.nodeBuilder = config.nodeBuilder;
	}

	@Override
	public void addBefore(Node nodeToAdd, Node anchor) {
		PsiElement nodeToAddDelegate = this.extractor.getDelegateElement(PsiElement.class, nodeToAdd);
		PsiElement anchorDelegate = this.extractor.getDelegateElement(PsiElement.class, anchor);
		this.element.addBefore(nodeToAddDelegate, anchorDelegate);
	}

	@Override
	public void addAfter(Node nodeToAdd, Node anchor) {
		PsiElement nodeToAddDelegate = this.extractor.getDelegateElement(PsiElement.class, nodeToAdd);
		PsiElement anchorDelegate = this.extractor.getDelegateElement(PsiElement.class, anchor);
		this.element.addAfter(nodeToAddDelegate, anchorDelegate);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitNode(this);
	}

	@Override
	public void acceptChildren(AstVisitor astVisitor) {
		PsiElement child = element.getFirstChild();
		while (child != null) {
			this.delegateStore.getNodeFrom(child).accept(astVisitor);
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
	public void replace(Node nodeToReplaceWith) {
		PsiElement wrappedElement = this.extractor.getDelegateElement(PsiElement.class,
				nodeToReplaceWith);
		this.element.replace(wrappedElement);
		this.element = wrappedElement;
	}

	protected PsiElement getWrappedElement() {
		return this.element;
	}

	@Override
	public Node getParent() {
		return this.delegateStore.getNodeFrom(this.element.getParent());
	}

}
