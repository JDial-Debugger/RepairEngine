package ast;

import com.intellij.psi.PsiIfStatement;

public class PsiIfStatementDelegate extends PsiStatementDelegate implements IfStatementDelegate {
	protected PsiIfStatementDelegate(PsiIfStatement statement) {
		super(statement);
	}
}
