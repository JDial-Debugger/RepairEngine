package ast.psi;

import ast.interfaces.NodeBuilder;
import com.intellij.psi.PsiElement;

public class NodeConfig<TPsi extends PsiElement> {
	public TPsi delegateElement;
	public PsiElementExtractor elementExtractor;
	public DelegateStore delegateStore;
	public NodeBuilder nodeBuilder;

	public NodeConfig(
			TPsi delegateElement,
			PsiElementExtractor elementExtractor,
			DelegateStore delegateStore,
			NodeBuilder nodeBuilder) {
		this.delegateElement = delegateElement;
		this.elementExtractor = elementExtractor;
		this.delegateStore = delegateStore;
		this.nodeBuilder = nodeBuilder;
	}
}
