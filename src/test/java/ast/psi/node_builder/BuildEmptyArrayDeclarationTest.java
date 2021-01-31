package ast.psi.node_builder;

import ast.interfaces.DeclarationStatementDelegate;
import ast.interfaces.LiteralExpressionDelegate;
import ast.interfaces.Type;
import ast.interfaces.TypeDelegate;
import ast.psi.InvalidDimensionSizeException;
import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiLiteralExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildEmptyArrayDeclarationTest extends PsiNodeBuilderTestBase {

	private PsiDeclarationStatement mockDelegate;
	private DeclarationStatementDelegate mockNode;
	private TypeDelegate mockInputType;
	private Integer[] inputDimensions;
	private String inputName;

	private static final String EXPECTED_DEFAULT_VALUE = "0";
	private static final String SAMPLE_STATEMENT_TEXT = "int[2][2][1] array = {{{0}, {0}}};";


	@Test
	void buildEmptyArrayDeclaration() {

		this.setInputs(Type.INT, new Integer[] { 2, 2, 1 }, "array");

		this.createNodeMocks();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setInputs(Type type, Integer[] dimensions, String name) {
		this.mockInputType = mock(TypeDelegate.class);
		when(this.mockInputType.asEnum()).thenReturn(type);
		this.inputDimensions = dimensions;
		this.inputName = name;
	}

	private void createNodeMocks() {
		this.mockNode = mock(DeclarationStatementDelegate.class);
		this.mockDelegate = mock(PsiDeclarationStatement.class);
		this.mockLiteral(EXPECTED_DEFAULT_VALUE);
	}

	private void mockLiteral(String literalValue) {
		PsiLiteralExpression mockLiteralDelegate = mock(PsiLiteralExpression.class);
		when(this.mockElementFactory.createExpressionFromText(literalValue, null)).thenReturn(
				mockLiteralDelegate);

		LiteralExpressionDelegate mockLiteral = mock(LiteralExpressionDelegate.class);
		when(this.mockNodeFactory.getNode(mockLiteralDelegate)).thenReturn(mockLiteral);
		when(mockLiteral.toString()).thenReturn("0");
	}

	private void stubDependencies() {
		when(this.mockArrayStringBuilder.buildArrayDeclarationStatement(this.mockInputType,
				this.inputName,
				EXPECTED_DEFAULT_VALUE,
				this.inputDimensions)).thenReturn(SAMPLE_STATEMENT_TEXT);

		when(this.mockElementFactory.createStatementFromText(eq(SAMPLE_STATEMENT_TEXT),
				eq(null))).thenReturn(mockDelegate);

		when(this.mockNodeFactory.getNode(mockDelegate)).thenReturn(mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		DeclarationStatementDelegate actualResult
				= this.builderUnderTest.buildEmptyArrayDeclaration(this.mockInputType,
				this.inputName,
				this.inputDimensions);

		assertEquals(mockNode, actualResult, BAD_RETURN);
	}
}
