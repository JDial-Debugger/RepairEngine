package ast.psi;

import ast.interfaces.IfStatementDelegate;
import com.intellij.psi.PsiIfStatement;

public class PsiIfStatementDelegate extends PsiStatementDelegate implements IfStatementDelegate {
	protected PsiIfStatementDelegate(PsiIfStatement statement) {
		super(statement);
	}
}
