package ast.psi;

import ast.interfaces.AssignExpression;
import com.intellij.psi.PsiAssignmentExpression;

public class PsiAssignExpression extends PsiExpression
		implements AssignExpression {

	protected PsiAssignExpression(NodeConfig<? extends PsiAssignmentExpression> config) {
		super(config);
	}
}
