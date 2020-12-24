package ast.psi;

import ast.interfaces.DeclarationStatementDelegate;
import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;

import java.util.Map;

public class PsiDeclarationStatementDelegate extends PsiStatementDelegate
		implements DeclarationStatementDelegate {
	protected PsiDeclarationStatementDelegate(NodeConfig<? extends PsiDeclarationStatement> config) {
		super(config);
	}
}
