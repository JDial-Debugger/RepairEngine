package ast.interfaces;

public interface StatementDelegate extends NodeDelegate {
	/**
	 * Adds a statement immediately after this statement
	 * @return the statement added after this statement
	 */
	public StatementDelegate addAfter(StatementDelegate statement);
}
