package ast.psi;

import ast.interfaces.StatementDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiStatement;

public class PsiStatementDelegate extends PsiNodeDelegateBase implements StatementDelegate {
	protected PsiStatementDelegate(PsiStatement statement) {
		super(statement);
	}

	@Override
	public void accept(VisitorDelegate visitor) {
		visitor.visitStatement(this);
	}
}
