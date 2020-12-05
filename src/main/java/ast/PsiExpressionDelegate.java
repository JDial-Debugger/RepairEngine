package ast;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;

public class PsiExpressionDelegate extends PsiNodeDelegate implements  ExpressionDelegate{

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
}
