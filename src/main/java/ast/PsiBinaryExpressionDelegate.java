package ast;

import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiExpression;

public class PsiBinaryExpressionDelegate extends PsiExpressionDelegate implements BinaryExpressionDelegate {

	protected PsiBinaryExpressionDelegate(PsiBinaryExpression expression) {
		super(expression);
	}
}
