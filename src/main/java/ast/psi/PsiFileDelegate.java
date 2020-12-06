package ast.psi;

import ast.interfaces.FileDelegate;
import com.intellij.psi.PsiFile;

public class PsiFileDelegate extends PsiNodeDelegateBase implements FileDelegate {
	protected PsiFileDelegate(PsiFile file) {
		super(file);
	}
}
