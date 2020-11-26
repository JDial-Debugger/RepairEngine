package ast;

import com.intellij.psi.PsiStatement;

public class PsiStatementDelegate extends PsiNodeDelegate implements  StatementDelegate{
	protected PsiStatementDelegate(PsiStatement statement) {
		super(statement);
	}
}
