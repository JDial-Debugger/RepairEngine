package ast.psi;

import ast.interfaces.AssertStatementDelegate;
import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiAssertStatement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;

import java.util.Map;

public class PsiAssertStatementDelegate extends PsiStatementDelegate
		implements AssertStatementDelegate {

	protected PsiAssertStatementDelegate(NodeConfig<? extends PsiAssertStatement> config) {
		super(config);
	}
}
