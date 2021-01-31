package ast.psi;

import ast.interfaces.IfStatement;

public class PsiIfStatement extends PsiStatement implements IfStatement {
	protected PsiIfStatement(NodeConfig<? extends com.intellij.psi.PsiIfStatement> config) {
		super(config);
	}
}
