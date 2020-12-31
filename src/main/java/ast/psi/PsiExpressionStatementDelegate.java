package ast.psi;

import ast.interfaces.ExpressionStatementDelegate;
import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiExpressionStatement;

import java.util.Map;

public class PsiExpressionStatementDelegate extends PsiStatementDelegate
		implements ExpressionStatementDelegate {
	protected PsiExpressionStatementDelegate(NodeConfig<? extends  PsiExpressionStatement> config) {
		super(config);
	}
}
