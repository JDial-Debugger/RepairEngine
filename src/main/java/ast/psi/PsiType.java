package ast.psi;

import ast.interfaces.PrimitiveType;
import ast.interfaces.Type;
import com.google.common.collect.ImmutableMap;

import java.util.Objects;

public class PsiType implements Type {

	private static final ImmutableMap<com.intellij.psi.PsiType, PrimitiveType> psiTypeToEnum = ImmutableMap.<com.intellij.psi.PsiType, PrimitiveType>builder()
			.put(com.intellij.psi.PsiType.BOOLEAN, PrimitiveType.BOOLEAN)
			.put(com.intellij.psi.PsiType.BYTE, PrimitiveType.BYTE)
			.put(com.intellij.psi.PsiType.CHAR, PrimitiveType.CHAR)
			.put(com.intellij.psi.PsiType.DOUBLE, PrimitiveType.DOUBLE)
			.put(com.intellij.psi.PsiType.FLOAT, PrimitiveType.FLOAT)
			.put(com.intellij.psi.PsiType.INT, PrimitiveType.INT)
			.put(com.intellij.psi.PsiType.LONG, PrimitiveType.LONG)
			.put(com.intellij.psi.PsiType.NULL, PrimitiveType.NULL)
			.put(com.intellij.psi.PsiType.SHORT, PrimitiveType.SHORT)
			.put(com.intellij.psi.PsiType.VOID, PrimitiveType.VOID)
			.build();
/*
	public static final PsiType BYTE = new PsiType(PsiType.BYTE);
	public static final PsiType BOOLEAN = new PsiType(PsiType.BOOLEAN);
	public static final PsiType CHAR = new PsiType(PsiType.CHAR);
	public static final PsiType DOUBLE = new PsiType(PsiType.DOUBLE);
	public static final PsiType FLOAT = new PsiType(PsiType.FLOAT);
	public static final PsiType INT = new PsiType(PsiType.INT);
	public static final PsiType LONG = new PsiType(PsiType.LONG);
	public static final PsiType NULL = new PsiType(PsiType.NULL);
	public static final PsiType SHORT = new PsiType(PsiType.SHORT);
	public static final PsiType VOID = new PsiType(PsiType.VOID);
*/
	private com.intellij.psi.PsiType type;

	protected PsiType(com.intellij.psi.PsiType type) {
		this.type = type;
	}

	protected com.intellij.psi.PsiType getWrappedType() {
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
		PsiType that = (PsiType) o;
		return type.equals(that.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type);
	}

	@Override
	public PrimitiveType asEnum() {
		return psiTypeToEnum.get(this.type);
	}
}
