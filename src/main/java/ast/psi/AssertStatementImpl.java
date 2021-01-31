package ast.psi;

import ast.interfaces.AssertStatement;

public class AssertStatementImpl extends StatementImpl
		implements AssertStatement {

	protected AssertStatementImpl(NodeConfig<? extends com.intellij.psi.PsiAssertStatement> config) {
		super(config);
	}
}
