package ast.interfaces;

public interface CodeBlock extends Node {
	public void addStatement(Statement statement);

	void addStatements(Statement... statements);
}
