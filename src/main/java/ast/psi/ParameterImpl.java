package ast.psi;

import ast.interfaces.Parameter;
import ast.interfaces.Type;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiParameter;
import com.intellij.psi.PsiType;

public class ParameterImpl extends NodeImplBase implements Parameter {
	protected ParameterImpl(NodeConfig<? extends PsiParameter> config) {
		super(config);
	}

	@Override
	public String getName() {
		return ((PsiParameter) this.element).getName();
	}

	@Override
	public Type getType() {
		PsiType type = ((PsiParameter) this.element).getType();
		return this.delegateStore.getTypeFrom(type);
	}
}
