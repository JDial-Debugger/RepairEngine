package ast.interfaces;

public interface CodeBlockDelegate extends NodeDelegate {
	public void addStatement(StatementDelegate statement);

	void addStatements(StatementDelegate... statements);
}
