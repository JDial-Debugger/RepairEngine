package ast.psi;

import ast.interfaces.AssignExpressionDelegate;
import com.intellij.psi.PsiAssignmentExpression;

public class PsiAssignExpressionDelegate extends PsiExpressionDelegate implements
		AssignExpressionDelegate {

	protected PsiAssignExpressionDelegate(PsiAssignmentExpression expression) {
		super(expression);
	}
}
