package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.LocalVariable;
import ast.interfaces.Type;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.PsiType;

public class LocalVariableImpl extends NodeImplBase implements LocalVariable {
	protected LocalVariableImpl(NodeConfig<? extends PsiLocalVariable> config) {
		super(config);
	}

	@Override
	public String getName() {
		return ((PsiLocalVariable) this.element).getName();
	}

	@Override
	public Type getType() {
		PsiType delegate = ((PsiLocalVariable) this.element).getType();
		return this.delegateStore.getTypeFrom(delegate);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitLocalVariable(this);
	}
}
