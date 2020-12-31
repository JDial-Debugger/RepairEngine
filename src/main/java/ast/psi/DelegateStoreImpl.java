package ast.psi;

import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;

import java.util.Map;

public class DelegateStoreImpl implements DelegateStore {

	private Map<PsiElement, NodeDelegate> delegateToNode;
	private NodeFactory nodeFactory;

	public DelegateStoreImpl(Map<PsiElement, NodeDelegate> delegateToNode, NodeFactory nodeFactory) {
		this.delegateToNode = delegateToNode;
		this.nodeFactory = nodeFactory;
	}

	@Override
	public NodeDelegate getNodeFrom(PsiElement delegate) {
		if (this.delegateToNode.containsKey(delegate)) {
			return this.delegateToNode.get(delegate);
		}
		NodeDelegate wrapper = this.nodeFactory.getNode(delegate);
		this.delegateToNode.put(delegate, wrapper);
		return wrapper;
	}
}
