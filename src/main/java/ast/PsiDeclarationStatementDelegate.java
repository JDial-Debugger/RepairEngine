package ast;

import com.intellij.psi.PsiDeclarationStatement;

public class PsiDeclarationStatementDelegate extends PsiStatementDelegate implements DeclarationStatementDelegate {
	public PsiDeclarationStatementDelegate(PsiDeclarationStatement statement) {
		super(statement);
	}
}
