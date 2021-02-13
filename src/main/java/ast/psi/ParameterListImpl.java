package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.Parameter;
import ast.interfaces.ParameterList;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiParameterList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParameterListImpl extends NodeImplBase implements ParameterList {
	protected ParameterListImpl(NodeConfig<? extends com.intellij.psi.PsiParameterList> config) {
		super(config);
	}

	@Override
	public Parameter[] getParameters() {

		PsiParameter[] paramDelegates = ((PsiParameterList) this.element).getParameters();
		Parameter[] params = new Parameter[paramDelegates.length];
		for (int i = 0; i < paramDelegates.length; ++i) {
			params[i] = (Parameter) this.delegateStore.getNodeFrom(paramDelegates[i]);
		}
		return params;
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitParameterList(this);
	}
}
