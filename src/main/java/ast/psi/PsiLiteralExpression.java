package ast.psi;

import ast.interfaces.LiteralExpression;

public class PsiLiteralExpression extends PsiExpression
		implements LiteralExpression {
	protected PsiLiteralExpression(NodeConfig<? extends com.intellij.psi.PsiLiteralExpression> config) {
		super(config);
	}

}
