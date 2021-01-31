package ast.psi.factory;

import ast.interfaces.Type;

public interface ArrayStringBuilder {
	String buildArrayDeclarationStatement(
			Type type,
			String name,
			String initValue,
			Integer[] dimensions);

}
