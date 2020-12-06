package ast.psi;

import ast.interfaces.ReturnStatementDelegate;
import com.intellij.psi.PsiReturnStatement;

public class PsiReturnStatementDelegate extends PsiStatementDelegate implements
		ReturnStatementDelegate {
	protected PsiReturnStatementDelegate(PsiReturnStatement statement) {
		super(statement);
	}
}
