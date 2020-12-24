package ast.psi;

import ast.interfaces.Type;
import ast.interfaces.TypeDelegate;
import com.google.common.collect.ImmutableMap;
import com.intellij.psi.PsiType;

import java.util.Objects;

public class PsiTypeDelegate implements TypeDelegate {

	private static final ImmutableMap<PsiType, Type> psiTypeToEnum = ImmutableMap.<PsiType, Type>builder()
			.put(PsiType.BOOLEAN, Type.BOOLEAN)
			.put(PsiType.BYTE, Type.BYTE)
			.put(PsiType.CHAR, Type.CHAR)
			.put(PsiType.DOUBLE, Type.DOUBLE)
			.put(PsiType.FLOAT, Type.FLOAT)
			.put(PsiType.INT, Type.INT)
			.put(PsiType.LONG, Type.LONG)
			.put(PsiType.NULL, Type.NULL)
			.put(PsiType.SHORT, Type.SHORT)
			.put(PsiType.VOID, Type.VOID)
			.build();
/*
	public static final PsiTypeDelegate BYTE = new PsiTypeDelegate(PsiType.BYTE);
	public static final PsiTypeDelegate BOOLEAN = new PsiTypeDelegate(PsiType.BOOLEAN);
	public static final PsiTypeDelegate CHAR = new PsiTypeDelegate(PsiType.CHAR);
	public static final PsiTypeDelegate DOUBLE = new PsiTypeDelegate(PsiType.DOUBLE);
	public static final PsiTypeDelegate FLOAT = new PsiTypeDelegate(PsiType.FLOAT);
	public static final PsiTypeDelegate INT = new PsiTypeDelegate(PsiType.INT);
	public static final PsiTypeDelegate LONG = new PsiTypeDelegate(PsiType.LONG);
	public static final PsiTypeDelegate NULL = new PsiTypeDelegate(PsiType.NULL);
	public static final PsiTypeDelegate SHORT = new PsiTypeDelegate(PsiType.SHORT);
	public static final PsiTypeDelegate VOID = new PsiTypeDelegate(PsiType.VOID);
*/
	private PsiType type;

	protected PsiTypeDelegate(PsiType type) {
		this.type = type;
	}

	protected PsiType getWrappedType() {
		return this.type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PsiTypeDelegate that = (PsiTypeDelegate) o;
		return type.equals(that.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

	@Override
	public Type asEnum() {
		return psiTypeToEnum.get(this.type);
	}
}
