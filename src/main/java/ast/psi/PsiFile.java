package ast.psi;

import ast.interfaces.File;

public class PsiFile extends PsiNodeBase implements File {
	protected PsiFile(NodeConfig<? extends com.intellij.psi.PsiFile> config) {
		super(config);
	}
}
