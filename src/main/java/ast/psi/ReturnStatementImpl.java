package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.ReturnStatement;

public class ReturnStatementImpl extends StatementImpl
		implements ReturnStatement {
	protected ReturnStatementImpl(NodeConfig<? extends com.intellij.psi.PsiReturnStatement> config) {
		super(config);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitReturnStatement(this);
	}
}
