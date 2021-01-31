package ast.psi;

import ast.interfaces.Method;
import ast.interfaces.ParameterList;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameterList;

public class MethodImpl extends NodeImplBase implements Method {

	protected MethodImpl(NodeConfig<? extends PsiMethod> config) {
		super(config);
	}

	@Override
	public ParameterList getParameterList() {
		PsiParameterList paramListDelegate = ((PsiMethod) this.element).getParameterList();
		return (ParameterList) this.delegateStore.getNodeFrom(paramListDelegate);
	}
}
