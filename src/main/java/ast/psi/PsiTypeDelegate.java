package ast.psi;

import ast.interfaces.Type;
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

	@Override
	public Type asEnum() {
		if (PsiType.BYTE.equals(type)) {
			return Type.BYTE;
		} else if (PsiType.CHAR.equals(type)) {
			return Type.CHAR;
		} else if (PsiType.DOUBLE.equals(type)) {
			return Type.DOUBLE;
		} else if (PsiType.FLOAT.equals(type)) {
			return Type.FLOAT;
		} else if (PsiType.INT.equals(type)) {
			return Type.INT;
		} else if (PsiType.LONG.equals(type)) {
			return Type.LONG;
		} else if (PsiType.SHORT.equals(type)) {
			return Type.SHORT;
		} else if (PsiType.BOOLEAN.equals(type)) {
			return Type.BOOLEAN;
		} else if (PsiType.VOID.equals(type)) {
			return Type.VOID;
		} else if (type == PsiType.NULL) {
			return Type.NULL;
		} else {
			return Type.OBJECT;
		}
	}
}
