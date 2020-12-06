package ast.psi;

import ast.interfaces.ExpressionDelegate;
import ast.interfaces.TypeDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiExpression;

public class PsiExpressionDelegate extends PsiNodeDelegateBase implements ExpressionDelegate {

	protected PsiExpressionDelegate(PsiExpression expression) {
		super(expression);
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
