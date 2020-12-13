package ast.psi;

import ast.interfaces.DeclarationStatementDelegate;
import com.intellij.psi.PsiDeclarationStatement;

public class PsiDeclarationStatementDelegate extends PsiStatementDelegate implements
		DeclarationStatementDelegate {
	protected PsiDeclarationStatementDelegate(PsiDeclarationStatement statement) {
		super(statement);
	}
}
