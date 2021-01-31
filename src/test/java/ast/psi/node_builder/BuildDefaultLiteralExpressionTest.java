package ast.psi.node_builder;

import ast.interfaces.LiteralExpression;
import ast.interfaces.PrimitiveType;
import ast.interfaces.Type;
import ast.psi.InvalidTypeException;
import com.intellij.psi.PsiLiteralExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildDefaultLiteralExpressionTest extends PsiNodeBuilderTestBase {

	private String expectedDefaultLiteral;
	private PrimitiveType typeToTest;
	private Type mockInputType;

	private PsiLiteralExpression mockDelegate;
	private LiteralExpression mockNode;


	@Test
	void buildDefaultLiteralExpressionForByte() {
		this.expectedDefaultLiteral = "0";
		this.typeToTest = PrimitiveType.BYTE;
		this.buildDefaultLiteralExpression();
	}

	private void buildDefaultLiteralExpression() {

		this.setBuilderInput();

		this.createNodeMocks();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setBuilderInput() {
		this.mockInputType = mock(Type.class);
		when(this.mockInputType.asEnum()).thenReturn(this.typeToTest);
	}

	private void createNodeMocks() {
		this.mockNode = mock(LiteralExpression.class);
		this.mockDelegate = mock(PsiLiteralExpression.class);
	}

	private void stubDependencies() {
		when(this.mockElementFactory.createExpressionFromText(this.expectedDefaultLiteral,
				null)).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		LiteralExpression actualResult
				= this.builderUnderTest.buildDefaultLiteralExpressionFor(this.mockInputType);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}

	@Test
	void buildDefaultLiteralExpressionForBoolean() {
		this.expectedDefaultLiteral = "false";
		this.typeToTest = PrimitiveType.BOOLEAN;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForChar() {
		this.expectedDefaultLiteral = "'\\u0000'";
		this.typeToTest = PrimitiveType.CHAR;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForDouble() {
		this.expectedDefaultLiteral = "0.0d";
		this.typeToTest = PrimitiveType.DOUBLE;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForFloat() {
		this.expectedDefaultLiteral = "0.0f";
		this.typeToTest = PrimitiveType.FLOAT;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForInt() {
		this.expectedDefaultLiteral = "0";
		this.typeToTest = PrimitiveType.INT;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForLong() {
		this.expectedDefaultLiteral = "0.0L";
		this.typeToTest = PrimitiveType.LONG;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForShort() {
		this.expectedDefaultLiteral = "0";
		this.typeToTest = PrimitiveType.SHORT;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForVoid() {
		this.expectedDefaultLiteral = PrimitiveType.VOID.toString();
		this.typeToTest = PrimitiveType.VOID;
		this.buildDefaultLiteralExpressionForBadType();
	}

	@Test
	void buildDefaultLiteralExpressionForNullEnum() {
		this.expectedDefaultLiteral = PrimitiveType.NULL.toString();
		this.typeToTest = PrimitiveType.NULL;
		this.buildDefaultLiteralExpressionForBadType();
	}

	@Test
	void buildDefaultLiteralExpressionForNull() {
		this.expectedDefaultLiteral = "null";
		this.typeToTest = null;
		this.buildDefaultLiteralExpressionForBadType();
	}

	private void buildDefaultLiteralExpressionForBadType() {

		this.setBuilderInput();

		this.assertBuilderThrowsTypeException();
	}

	private void assertBuilderThrowsTypeException() {
		InvalidTypeException ex = assertThrows(InvalidTypeException.class,
				() -> this.builderUnderTest.buildDefaultLiteralExpressionFor(this.mockInputType));

		String expectedMessage = "Provided type " + this.expectedDefaultLiteral + " does not have a default value";
		assertEquals(expectedMessage, ex.getMessage(), "Expected error message was incorrect");
	}
}
