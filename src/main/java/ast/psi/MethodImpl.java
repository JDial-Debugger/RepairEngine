package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.CodeBlock;
import ast.interfaces.Method;
import ast.interfaces.ParameterList;
import com.intellij.psi.PsiCodeBlock;
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

	@Override
	public CodeBlock getBody() {
		PsiCodeBlock delegate = ((PsiMethod) this.element).getBody();
		return (CodeBlock) this.delegateStore.getNodeFrom(delegate);
	}

	@Override
	public String getName() {
		return ((PsiMethod) this.element).getName();
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitMethod(this);
	}
}
