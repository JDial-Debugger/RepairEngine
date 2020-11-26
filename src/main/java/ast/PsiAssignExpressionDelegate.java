package ast;

import com.intellij.psi.PsiAssignmentExpression;
import com.intellij.psi.PsiExpression;

public class PsiAssignExpressionDelegate extends PsiExpressionDelegate implements AssignExpressionDelegate {

	protected PsiAssignExpressionDelegate(PsiAssignmentExpression expression) {
		super(expression);
	}
}
