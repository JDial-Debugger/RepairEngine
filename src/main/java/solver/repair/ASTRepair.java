package solver.repair;

import ast.interfaces.FileDelegate;

import java.util.Map;

public interface ASTRepair {
	public Map<Integer, String> getModifiedLines(FileDelegate fileDelegate);
}
