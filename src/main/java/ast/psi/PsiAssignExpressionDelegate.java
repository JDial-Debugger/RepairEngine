package ast.psi;

import ast.interfaces.AssignExpressionDelegate;
<<<<<<< HEAD
import ast.interfaces.VisitorDelegate;
=======
import ast.interfaces.ExpressionDelegate;
>>>>>>> 7534634... Update the full logic for Assignment Expression for `getSolvableCode`
import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiAssignmentExpression;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiExpression;

public class PsiAssignExpressionDelegate extends PsiExpressionDelegate
		implements AssignExpressionDelegate {

	protected PsiAssignExpressionDelegate(NodeConfig<? extends PsiAssignmentExpression> config) {
		super(config);
	}

	@Override
	public void accept(VisitorDelegate visitor) {
		visitor.visitAssignExpression(this);
	}

	@Override
	public ExpressionDelegate getLExpression() {
		PsiAssignmentExpression expr = (PsiAssignmentExpression) this.element;
		PsiExpression lhs = expr.getLExpression();
		return (ExpressionDelegate) this.delegateStore.getNodeFrom(lhs);
	}

	/**
	 * Get the expression on the right side of an assign expression
	 *
	 * Example:
	 * a = 4;
	 *     ~~
	 * Get this `4` on the right side
	 *
	 * @return an expression delegate object from delegateStore
	 */
	@Override
	public ExpressionDelegate getRExpression() {
		PsiAssignmentExpression expr = (PsiAssignmentExpression) this.element;
		PsiExpression rhs = expr.getRExpression();
		return (ExpressionDelegate) this.delegateStore.getNodeFrom(rhs);
	}
}
