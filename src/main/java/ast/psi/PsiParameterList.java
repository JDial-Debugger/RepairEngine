package ast.psi;

import ast.interfaces.ParameterList;

public class PsiParameterList extends PsiNodeBase implements ParameterList {
	protected PsiParameterList(NodeConfig<? extends com.intellij.psi.PsiParameterList> config) {
		super(config);
	}
}
