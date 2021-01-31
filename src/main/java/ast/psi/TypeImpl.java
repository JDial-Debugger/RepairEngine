package ast.psi;

import ast.interfaces.PrimitiveType;
import ast.interfaces.Type;
import com.google.common.collect.ImmutableMap;

import java.util.Objects;

public class TypeImpl implements Type {

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
	public static final TypeImpl BYTE = new TypeImpl(TypeImpl.BYTE);
	public static final TypeImpl BOOLEAN = new TypeImpl(TypeImpl.BOOLEAN);
	public static final TypeImpl CHAR = new TypeImpl(TypeImpl.CHAR);
	public static final TypeImpl DOUBLE = new TypeImpl(TypeImpl.DOUBLE);
	public static final TypeImpl FLOAT = new TypeImpl(TypeImpl.FLOAT);
	public static final TypeImpl INT = new TypeImpl(TypeImpl.INT);
	public static final TypeImpl LONG = new TypeImpl(TypeImpl.LONG);
	public static final TypeImpl NULL = new TypeImpl(TypeImpl.NULL);
	public static final TypeImpl SHORT = new TypeImpl(TypeImpl.SHORT);
	public static final TypeImpl VOID = new TypeImpl(TypeImpl.VOID);
*/
	private com.intellij.psi.PsiType type;

	protected TypeImpl(com.intellij.psi.PsiType type) {
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
		TypeImpl that = (TypeImpl) o;
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
