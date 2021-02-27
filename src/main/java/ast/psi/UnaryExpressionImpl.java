package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.UnaryExpression;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiUnaryExpression;

public class UnaryExpressionImpl extends ExpressionImpl implements UnaryExpression {
	protected UnaryExpressionImpl(NodeConfig<? extends PsiUnaryExpression> config) {
		super(config);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitUnaryExpression(this);
	}
}
