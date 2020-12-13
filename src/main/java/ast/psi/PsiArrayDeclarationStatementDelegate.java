package ast.psi;

import ast.interfaces.ArrayDeclarationStatementDelegate;
import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiStatement;

public class PsiArrayDeclarationStatementDelegate extends PsiStatementDelegate implements
		ArrayDeclarationStatementDelegate {

	protected PsiArrayDeclarationStatementDelegate(PsiStatement statement) {
		super(statement);
	}

	@Override
	public String toString() {
		return null;
	}
}
