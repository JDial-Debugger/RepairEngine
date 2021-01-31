package ast.psi;

import ast.interfaces.Node;
import ast.interfaces.Type;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiType;

import java.util.Map;

public class DelegateStoreImpl implements DelegateStore {

	private Map<PsiElement, Node> delegateToNode;
	private Map<PsiType, Type> delegateToType;
	private NodeFactory nodeFactory;

	public DelegateStoreImpl(Map<PsiElement, Node> delegateToNode, Map<PsiType, Type> delegateToType, NodeFactory nodeFactory) {
		this.delegateToNode = delegateToNode;
		this.delegateToType = delegateToType;
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

	@Override
	public Type getTypeFrom(PsiType type) {
		if (this.delegateToType.containsKey(type)) {
			return this.delegateToType.get(type);
		}
		Type wrapper = this.nodeFactory.getType(type);
		this.delegateToType.put(type, wrapper);
		return wrapper;
	}
}
