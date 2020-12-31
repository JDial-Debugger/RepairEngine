package ast.psi;

import ast.interfaces.IfStatementDelegate;
import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIfStatement;
import com.intellij.psi.PsiStatement;

import java.util.Map;

public class PsiIfStatementDelegate extends PsiStatementDelegate implements IfStatementDelegate {
	protected PsiIfStatementDelegate(NodeConfig<? extends  PsiIfStatement> config) {
		super(config);
	}
}
