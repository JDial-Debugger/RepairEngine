package ast.interfaces;

import ast.psi.VisitorDelegate;

public interface NodeDelegate {
	public void accept(VisitorDelegate visitor);
	public void acceptChildren(VisitorDelegate visitor);
	public String toString();
}
