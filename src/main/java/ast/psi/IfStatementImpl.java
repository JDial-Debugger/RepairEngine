package ast.psi;

import ast.interfaces.IfStatement;

public class IfStatementImpl extends StatementImpl implements IfStatement {
	protected IfStatementImpl(NodeConfig<? extends com.intellij.psi.PsiIfStatement> config) {
		super(config);
	}
}
