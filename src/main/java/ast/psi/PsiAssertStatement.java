package ast.psi;

import ast.interfaces.AssertStatement;

public class PsiAssertStatement extends PsiStatement
		implements AssertStatement {

	protected PsiAssertStatement(NodeConfig<? extends com.intellij.psi.PsiAssertStatement> config) {
		super(config);
	}
}
