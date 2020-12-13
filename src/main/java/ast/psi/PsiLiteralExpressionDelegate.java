package ast.psi;

import ast.interfaces.LiteralExpressionDelegate;
import com.intellij.psi.PsiLiteralExpression;

public class PsiLiteralExpressionDelegate extends PsiExpressionDelegate implements
		LiteralExpressionDelegate {
	protected PsiLiteralExpressionDelegate(PsiLiteralExpression expression) {
		super(expression);
	}

}
