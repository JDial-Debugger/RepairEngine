package ast.psi;

import com.intellij.psi.PsiElement;

public class NodeConfig<TPsi extends PsiElement> {
	public TPsi delegateElement;
	public PsiElementExtractor elementExtractor;
	public DelegateStore delegateStore;

	public NodeConfig(TPsi delegateElement, PsiElementExtractor elementExtractor, DelegateStore delegateStore) {
		this.delegateElement = delegateElement;
		this.elementExtractor = elementExtractor;
		this.delegateStore = delegateStore;
	}
}
