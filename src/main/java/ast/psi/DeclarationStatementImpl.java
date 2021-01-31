package ast.psi;

import ast.interfaces.DeclarationStatement;

public class DeclarationStatementImpl extends StatementImpl
		implements DeclarationStatement {
	protected DeclarationStatementImpl(NodeConfig<? extends com.intellij.psi.PsiDeclarationStatement> config) {
		super(config);
	}
}
