package ast.psi;

import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiStatement;

public class DelegateFactoryImpl implements  DelegateFactory {
	@Override
	public NodeDelegate getNode(PsiElement wrappedElement) {
		return new PsiNodeDelegateBase(wrappedElement);
	}

	@Override
	public NodeDelegate getNode(PsiExpression wrappedExpression) {
		return new PsiExpressionDelegate(wrappedExpression);
	}

	@Override
	public NodeDelegate getNode(PsiStatement wrappedStatement) {
		return new PsiStatementDelegate(wrappedStatement);
	}

	@Override
	public NodeDelegate getNode(PsiBinaryExpression wrappedExpression) {
		return new PsiBinaryExpressionDelegate(wrappedExpression);
	}

}
