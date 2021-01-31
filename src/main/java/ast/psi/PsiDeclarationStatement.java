package ast.psi;

import ast.interfaces.DeclarationStatement;

public class PsiDeclarationStatement extends PsiStatement
		implements DeclarationStatement {
	protected PsiDeclarationStatement(NodeConfig<? extends com.intellij.psi.PsiDeclarationStatement> config) {
		super(config);
	}
}
