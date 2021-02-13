package ast.interfaces;

import java.util.Set;

public interface Node {
	void addBefore(Node nodeToAdd, Node anchor);
	void addAfter(Node nodeToAdd, Node anchor);

	public void accept(AstVisitor astVisitor);
	public void acceptChildren(AstVisitor astVisitor);
	public Set<String> getVariableNamesInScope();
	public String toString();

	void replace(Node nodeToReplaceWith);

	Node getParent();
}
