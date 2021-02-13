package ast.interfaces;

public interface Method extends Node {
	ParameterList getParameterList();
	CodeBlock getBody();

	String getName();
}
