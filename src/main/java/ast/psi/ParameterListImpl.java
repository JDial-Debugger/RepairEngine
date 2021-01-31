package ast.psi;

import ast.interfaces.ParameterList;

public class ParameterListImpl extends NodeImplBase implements ParameterList {
	protected ParameterListImpl(NodeConfig<? extends com.intellij.psi.PsiParameterList> config) {
		super(config);
	}
}
