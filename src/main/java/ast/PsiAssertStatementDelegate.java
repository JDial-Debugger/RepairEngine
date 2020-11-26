package ast;

import com.intellij.psi.PsiAssertStatement;

public class PsiAssertStatementDelegate extends PsiStatementDelegate implements AssertStatementDelegate {
	protected PsiAssertStatementDelegate(PsiAssertStatement statement) {
		super(statement);
	}
}
