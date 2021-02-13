package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.ExpressionStatement;

public class ExpressionStatementImpl extends StatementImpl
		implements ExpressionStatement {
	protected ExpressionStatementImpl(NodeConfig<? extends com.intellij.psi.PsiExpressionStatement> config) {
		super(config);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitExpressionStatement(this);
	}
}
