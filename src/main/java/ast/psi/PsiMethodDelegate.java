package ast.psi;

import ast.interfaces.MethodDelegate;
import com.intellij.psi.PsiMethod;

public class PsiMethodDelegate extends PsiNodeDelegateBase implements MethodDelegate {

	protected PsiMethodDelegate(PsiMethod method) {
		super(method);
	}
}
