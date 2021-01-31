package ast.psi;

import ast.interfaces.ReturnStatement;

public class ReturnStatementImpl extends StatementImpl
		implements ReturnStatement {
	protected ReturnStatementImpl(NodeConfig<? extends com.intellij.psi.PsiReturnStatement> config) {
		super(config);
	}
}
