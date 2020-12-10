package ast.interfaces;

public interface NodeDelegate {
	public void accept(VisitorDelegate visitor);
	public void acceptChildren(VisitorDelegate visitor);
	public String toString();

	void replace(NodeDelegate nodeToReplaceWith);

	NodeDelegate getParent();
}
