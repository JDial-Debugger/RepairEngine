package ast.psi;

import ast.interfaces.ParameterListDelegate;
import com.intellij.psi.PsiParameterList;

public class PsiParameterListDelegate extends PsiNodeDelegate implements ParameterListDelegate {
	protected PsiParameterListDelegate(PsiParameterList element) {
		super(element);
	}
}
