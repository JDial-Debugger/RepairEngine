package ast.psi;

import ast.interfaces.MethodDelegate;
import com.intellij.psi.PsiMethod;

public class PsiMethodDelegate extends PsiNodeDelegate implements MethodDelegate {

	protected PsiMethodDelegate(PsiMethod method) {
		super(method);
	}
}
