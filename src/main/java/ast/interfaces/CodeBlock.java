package ast.interfaces;

public interface CodeBlock extends Node, Iterable<Statement> {
	public void addStatement(Statement statement);

	void addStatements(Statement... statements);
}
