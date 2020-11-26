package ast;

import ast.MethodDelegate;
import ast.VisitorDelegate;
import com.intellij.psi.PsiMethod;

public class PsiMethodDelegate extends PsiNodeDelegate implements MethodDelegate {

	protected PsiMethodDelegate(PsiMethod method) {
		super(method);
	}
}
