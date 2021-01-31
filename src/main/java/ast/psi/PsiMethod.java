package ast.psi;

import ast.interfaces.Method;

public class PsiMethod extends PsiNodeBase implements Method {

	protected PsiMethod(NodeConfig<? extends com.intellij.psi.PsiMethod> config) {
		super(config);
	}
}
