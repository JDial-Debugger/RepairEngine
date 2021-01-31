package solver.repair;

import ast.interfaces.File;

import java.util.Map;

public interface ASTRepair {
	public Map<Integer, String> getModifiedLines(File fileDelegate);
}
