package ast.psi;

import ast.interfaces.TypeDelegate;
import com.intellij.psi.PsiType;

public class PsiTypeDelegate implements TypeDelegate {


	private PsiType type;

	protected PsiTypeDelegate(PsiType type) {
		this.type = type;
	}

	protected PsiType getWrappedType() {
		return this.type;
	}
}
