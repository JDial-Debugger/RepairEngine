package ast;

import com.intellij.psi.PsiExpressionStatement;
import com.intellij.psi.PsiStatement;

public class PsiExpressionStatementDelegate extends PsiStatementDelegate implements ExpressionStatementDelegate{
	protected PsiExpressionStatementDelegate(PsiExpressionStatement statement) {
		super(statement);
	}
}
