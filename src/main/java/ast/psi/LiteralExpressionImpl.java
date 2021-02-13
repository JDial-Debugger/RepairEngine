package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.LiteralExpression;

public class LiteralExpressionImpl extends ExpressionImpl
		implements LiteralExpression {
	protected LiteralExpressionImpl(NodeConfig<? extends com.intellij.psi.PsiLiteralExpression> config) {
		super(config);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitLiteralExpression(this);
	}
}
