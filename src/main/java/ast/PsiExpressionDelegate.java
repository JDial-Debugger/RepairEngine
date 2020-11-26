package ast;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;

public class PsiExpressionDelegate extends PsiNodeDelegate implements  ExpressionDelegate{

	protected PsiExpressionDelegate(PsiExpression expression) {
		super(expression);
	}
}
