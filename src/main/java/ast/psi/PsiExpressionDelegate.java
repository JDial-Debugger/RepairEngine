package ast.psi;

import ast.interfaces.ExpressionDelegate;
import ast.interfaces.NodeDelegate;
import ast.interfaces.TypeDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiStatement;

import java.util.Map;

public class PsiExpressionDelegate extends PsiNodeDelegateBase implements ExpressionDelegate {

	protected PsiExpressionDelegate(NodeConfig<? extends PsiExpression> config) {
		super(config);
	}

	protected PsiExpression getWrappedExpression() {
		return (PsiExpression) super.element;
	}

	@Override
	public TypeDelegate getType() {
		return new PsiTypeDelegate(((PsiExpression) this.element).getType());
	}

	@Override
	public void accept(VisitorDelegate visitor) {
		visitor.visitExpression(this);
	}
}
