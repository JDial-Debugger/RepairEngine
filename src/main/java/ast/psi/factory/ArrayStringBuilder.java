package ast.psi.factory;

import ast.interfaces.TypeDelegate;

public interface ArrayStringBuilder {
	String buildArrayDeclarationStatement(
			TypeDelegate type,
			String name,
			String initValue,
			Integer[] dimensions);

}
