package ast.psi;

import ast.interfaces.LiteralExpression;

public class LiteralExpressionImpl extends ExpressionImpl
		implements LiteralExpression {
	protected LiteralExpressionImpl(NodeConfig<? extends com.intellij.psi.PsiLiteralExpression> config) {
		super(config);
	}

}
