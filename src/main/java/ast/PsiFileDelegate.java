package ast;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

public class PsiFileDelegate extends PsiNodeDelegate implements FileDelegate {
	protected PsiFileDelegate(PsiFile file) {
		super(file);
	}
}
