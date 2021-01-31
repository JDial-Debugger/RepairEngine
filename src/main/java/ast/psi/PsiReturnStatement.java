package ast.psi;

import ast.interfaces.ReturnStatement;

public class PsiReturnStatement extends PsiStatement
		implements ReturnStatement {
	protected PsiReturnStatement(NodeConfig<? extends com.intellij.psi.PsiReturnStatement> config) {
		super(config);
	}
}
