package ast.psi;

import ast.interfaces.Node;
import com.intellij.psi.PsiElement;

import java.util.Map;

public class DelegateStoreImpl implements DelegateStore {

	private Map<PsiElement, Node> delegateToNode;
	private NodeFactory nodeFactory;

	public DelegateStoreImpl(Map<PsiElement, Node> delegateToNode, NodeFactory nodeFactory) {
		this.delegateToNode = delegateToNode;
		this.nodeFactory = nodeFactory;
	}

	@Override
	public Node getNodeFrom(PsiElement delegate) {
		if (this.delegateToNode.containsKey(delegate)) {
			return this.delegateToNode.get(delegate);
		}
		Node wrapper = this.nodeFactory.getNode(delegate);
		this.delegateToNode.put(delegate, wrapper);
		return wrapper;
	}
}
