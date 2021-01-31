package ast.psi;

import ast.interfaces.File;

public class FileImpl extends NodeImplBase implements File {
	protected FileImpl(NodeConfig<? extends com.intellij.psi.PsiFile> config) {
		super(config);
	}
}
