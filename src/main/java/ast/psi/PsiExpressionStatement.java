package ast.psi;

import ast.interfaces.ExpressionStatement;

public class PsiExpressionStatement extends PsiStatement
		implements ExpressionStatement {
	protected PsiExpressionStatement(NodeConfig<? extends com.intellij.psi.PsiExpressionStatement> config) {
		super(config);
	}
}
