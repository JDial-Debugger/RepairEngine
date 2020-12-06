package ast.psi;

import ast.interfaces.BinaryExpressionDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiBinaryExpression;

public class PsiBinaryExpressionDelegate extends PsiExpressionDelegate implements
		BinaryExpressionDelegate {

	protected PsiBinaryExpressionDelegate(PsiBinaryExpression expression) {
		super(expression);
	}

	@Override
	public void accept(VisitorDelegate visitor) {
		visitor.visitBinaryExpression(this);
	}
}
