package ast.interfaces;

public enum UnaryOperator {

	PRE_INCREMENT("++"),
	POST_INCREMENT("++"),
	POST_DECREMENT("--"),
	PRE_DECREMENT("--"),
	POSITIVE("+"),
	ARITHMETIC_INVERSE("-"),
	LOGICAL_INVERSE("!");

	private final String codeRepresentation;

	UnaryOperator(String codeRepresentation) {
		this.codeRepresentation = codeRepresentation;
	}

	public String toString() {
		return codeRepresentation;
	}
}
