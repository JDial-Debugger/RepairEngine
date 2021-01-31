package ast.interfaces;

public interface Statement extends Node {
	/**
	 * Adds a statement immediately after this statement
	 * @return the statement added after this statement
	 */
	public Statement addAfter(Statement statement);
}
