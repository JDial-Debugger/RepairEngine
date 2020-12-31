package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.ReturnStatementDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReturnStatement;

import java.util.Map;

public class PsiReturnStatementDelegate extends PsiStatementDelegate
		implements ReturnStatementDelegate {
	protected PsiReturnStatementDelegate(NodeConfig<? extends PsiReturnStatement> config) {
		super(config);
	}
}
