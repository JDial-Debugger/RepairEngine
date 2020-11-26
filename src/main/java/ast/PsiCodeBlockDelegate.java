package ast;

import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;

public class PsiCodeBlockDelegate extends PsiNodeDelegate implements CodeBlockDelegate {
	protected PsiCodeBlockDelegate(PsiCodeBlock element) {
		super(element);
	}
}
