package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.ForStatement;
import com.intellij.psi.PsiForStatement;

public class ForStatementImpl extends StatementImpl implements ForStatement {
	protected ForStatementImpl(NodeConfig<? extends PsiForStatement> config) {
		super(config);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitForStatement(this);
	}
}
