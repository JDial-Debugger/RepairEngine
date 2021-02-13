package ast.psi;

import ast.interfaces.AssignExpression;
import ast.interfaces.AstVisitor;
import com.intellij.psi.PsiAssignmentExpression;

public class AssignExpressionImpl extends ExpressionImpl
		implements AssignExpression {

	protected AssignExpressionImpl(NodeConfig<? extends PsiAssignmentExpression> config) {
		super(config);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitAssignExpression(this);
	}
}
