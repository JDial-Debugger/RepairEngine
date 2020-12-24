package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.ParameterListDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiReturnStatement;

import java.util.Map;

public class PsiParameterListDelegate extends PsiNodeDelegateBase implements ParameterListDelegate {
	protected PsiParameterListDelegate(NodeConfig<? extends PsiParameterList> config) {
		super(config);
	}

	public void getParameterList() {
		PsiParameterList list = (PsiParameterList) this.element;
		PsiParameter[] params = list.getParameters();
		for (PsiParameter p: params) {
			// iterate through each parameter and use delegate store to store them for further processing
			this.delegateStore.getNodeFrom(p);
		}
	}
}
