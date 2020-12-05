package ast;

import com.intellij.psi.PsiReturnStatement;
import com.intellij.psi.PsiStatement;

public class PsiReturnStatementDelegate extends PsiStatementDelegate implements  ReturnStatementDelegate {
	protected PsiReturnStatementDelegate(PsiReturnStatement statement) {
		super(statement);
	}
}
