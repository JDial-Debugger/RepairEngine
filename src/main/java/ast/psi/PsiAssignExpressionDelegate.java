package ast.psi;

import ast.interfaces.AssignExpressionDelegate;
import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiAssignmentExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;

import java.util.Map;

public class PsiAssignExpressionDelegate extends PsiExpressionDelegate
		implements AssignExpressionDelegate {

	protected PsiAssignExpressionDelegate(NodeConfig<? extends PsiAssignmentExpression> config) {
		super(config);
	}
}
