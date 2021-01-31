package ast.psi.node_builder;

import ast.interfaces.LiteralExpressionDelegate;
import ast.interfaces.Type;
import ast.interfaces.TypeDelegate;
import ast.psi.InvalidTypeException;
import com.intellij.psi.PsiLiteralExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildDefaultLiteralExpressionTest extends PsiNodeBuilderTestBase {

	private String expectedDefaultLiteral;
	private Type typeToTest;
	private TypeDelegate mockInputType;

	private PsiLiteralExpression mockDelegate;
	private LiteralExpressionDelegate mockNode;


	@Test
	void buildDefaultLiteralExpressionForByte() {
		this.expectedDefaultLiteral = "0";
		this.typeToTest = Type.BYTE;
		this.buildDefaultLiteralExpression();
	}

	private void buildDefaultLiteralExpression() {

		this.setBuilderInput();

		this.createNodeMocks();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setBuilderInput() {
		this.mockInputType = mock(TypeDelegate.class);
		when(this.mockInputType.asEnum()).thenReturn(this.typeToTest);
	}

	private void createNodeMocks() {
		this.mockNode = mock(LiteralExpressionDelegate.class);
		this.mockDelegate = mock(PsiLiteralExpression.class);
	}

	private void stubDependencies() {
		when(this.mockElementFactory.createExpressionFromText(this.expectedDefaultLiteral,
				null)).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		LiteralExpressionDelegate actualResult
				= this.builderUnderTest.buildDefaultLiteralExpressionFor(this.mockInputType);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}

	@Test
	void buildDefaultLiteralExpressionForBoolean() {
		this.expectedDefaultLiteral = "false";
		this.typeToTest = Type.BOOLEAN;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForChar() {
		this.expectedDefaultLiteral = "'\\u0000'";
		this.typeToTest = Type.CHAR;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForDouble() {
		this.expectedDefaultLiteral = "0.0d";
		this.typeToTest = Type.DOUBLE;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForFloat() {
		this.expectedDefaultLiteral = "0.0f";
		this.typeToTest = Type.FLOAT;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForInt() {
		this.expectedDefaultLiteral = "0";
		this.typeToTest = Type.INT;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForLong() {
		this.expectedDefaultLiteral = "0.0L";
		this.typeToTest = Type.LONG;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForShort() {
		this.expectedDefaultLiteral = "0";
		this.typeToTest = Type.SHORT;
		this.buildDefaultLiteralExpression();
	}

	@Test
	void buildDefaultLiteralExpressionForVoid() {
		this.expectedDefaultLiteral = Type.VOID.toString();
		this.typeToTest = Type.VOID;
		this.buildDefaultLiteralExpressionForBadType();
	}

	@Test
	void buildDefaultLiteralExpressionForNullEnum() {
		this.expectedDefaultLiteral = Type.NULL.toString();
		this.typeToTest = Type.NULL;
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
