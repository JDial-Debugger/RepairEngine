package ast.psi;

import ast.interfaces.LiteralExpressionDelegate;
import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiLiteralExpression;

import java.util.Map;

public class PsiLiteralExpressionDelegate extends PsiExpressionDelegate
		implements LiteralExpressionDelegate {
	protected PsiLiteralExpressionDelegate(NodeConfig<? extends PsiLiteralExpression> config) {
		super(config);
	}

}
