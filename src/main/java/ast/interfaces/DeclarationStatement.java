package ast.interfaces;

import java.util.List;

public interface DeclarationStatement extends Statement {
	List<LocalVariable> getDeclaredLocalVariables();
}
