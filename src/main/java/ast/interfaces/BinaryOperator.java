package ast.interfaces;

public enum BinaryOperator {

	ADD("+"),
	AND("&&"),
	BITWISE_AND("&"),
	BITWISE_OR("|"),
	DIVIDE("/"),
	EQUALS("=="),
	GREATER(">"),
	GREATER_EQUALS(">="),
	LESS("<"),
	LESS_EQUALS("<="),
	LEFT_SHIFT("<<"),
	MULTIPLY("*"),
	MODULO("%"),
	NOT_EQUALS("!="),
	OR("||"),
	SIGNED_RIGHT_SHIFT(">>"),
	SUBTRACT("-"),
	UNSIGNED_RIGHT_SHIFT(">>>"),
	XOR("^");

	private final String codeRepresentation;

	BinaryOperator(String codeRepresentation) {
		this.codeRepresentation = codeRepresentation;
	}

	public String toString() {
		return codeRepresentation;
	}
}