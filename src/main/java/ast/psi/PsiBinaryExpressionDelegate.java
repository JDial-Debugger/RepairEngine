package ast.psi;

import ast.interfaces.BinaryExpressionDelegate;
import com.intellij.psi.PsiBinaryExpression;

public class PsiBinaryExpressionDelegate extends PsiExpressionDelegate implements
		BinaryExpressionDelegate {

	protected PsiBinaryExpressionDelegate(PsiBinaryExpression expression) {
		super(expression);
	}
}
