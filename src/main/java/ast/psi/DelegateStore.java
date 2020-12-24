package ast.psi;

import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;

public interface DelegateStore {
	/**
	 * To save needlessly creating new wrapper objects for the delegates, this method checks to see
	 * if a node instance already exists for the given delegate object. If it doesn't it creates a
	 * new instance and adds it to the store.
	 *
	 * @param delegate - The raw PSI object to lookup
	 * @return - a node that wraps the given delegate
	 */
	public NodeDelegate getNodeFrom(PsiElement delegate);
}
