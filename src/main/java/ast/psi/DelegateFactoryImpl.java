package ast.psi;

import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;

public class DelegateFactoryImpl implements  DelegateFactory {
	@Override
	public NodeDelegate getNode(PsiElement wrappedElement) {
		return new PsiNodeDelegateBase(wrappedElement);
	}
}
