package ast.psi;

public class InvalidDimensionSizeException extends RuntimeException {
	public InvalidDimensionSizeException(int dimensionSize, int dimensionIndex) {
		super("Invalid dimension size "
				+ dimensionSize
				+ " provided on dimensions["
				+ dimensionIndex
				+ "]");
	}
}
