package ast.psi;

import ast.interfaces.AssignExpression;
import com.intellij.psi.PsiAssignmentExpression;

public class AssignExpressionImpl extends ExpressionImpl
		implements AssignExpression {

	protected AssignExpressionImpl(NodeConfig<? extends PsiAssignmentExpression> config) {
		super(config);
	}
}
