package ast.interfaces;

public enum AssignOperator {
	SIMPLE("="),
	ADD("+="),
	SUBTRACT("-="),
	MULTIPLY("*="),
	DIVIDE("/="),
	MODULO("%="),
	LEFT_SHIFT("<<="),
	RIGHT_SHIFT(">>="),
	BITWISE_AND("&="),
	BITWISE_OR("|="),
	BITWISE_XOR("^=");

	private final String codeRepresentation;

	AssignOperator(String codeRepresentation) {
		this.codeRepresentation = codeRepresentation;
	}


	@Override
	public String toString() {
		return this.codeRepresentation;
	}
}
