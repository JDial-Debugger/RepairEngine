package ast.psi;

import ast.interfaces.ExpressionStatementDelegate;
import com.intellij.psi.PsiExpressionStatement;

public class PsiExpressionStatementDelegate extends PsiStatementDelegate implements
		ExpressionStatementDelegate {
	protected PsiExpressionStatementDelegate(PsiExpressionStatement statement) {
		super(statement);
	}
}
