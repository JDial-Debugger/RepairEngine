package ast.interfaces;

import java.util.Set;

public interface NodeDelegate {
	public void accept(VisitorDelegate visitor);
	public void acceptChildren(VisitorDelegate visitor);
	public Set<String> getVariableNamesInScope();
	public String toString();

	void replace(NodeDelegate nodeToReplaceWith);

	NodeDelegate getParent();
}
