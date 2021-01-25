package ast.psi.node_builder;

import ast.interfaces.DeclarationStatementDelegate;
import ast.interfaces.LiteralExpressionDelegate;
import ast.interfaces.Type;
import ast.interfaces.TypeDelegate;
import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiLiteralExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildEmptyArrayDeclarationTest extends PsiNodeBuilderTestBase {

	private PsiDeclarationStatement mockDelegate;
	private DeclarationStatementDelegate mockNode;
	private TypeDelegate mockInputType;
	private Integer[] inputDimensions;

	private static final String INPUT_NAME = "array";
	private static final Integer[] INPUT_DIMENSIONS = new Integer[] { 2, 2, 1 };
	private static final String EXPECTED_DEFAULT_VALUE = "0";
	private static final String SAMPLE_STATEMENT_TEXT = "int[2][2][1] array = {{{0}, {0}}};";


	@Test
	void buildEmptyArrayDeclaration() {

		this.mockInputs();

		this.createNodeMocks();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void mockInputs() {
		this.mockInputType = mock(TypeDelegate.class);
		when(this.mockInputType.asEnum()).thenReturn(Type.INT);
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
				INPUT_NAME,
				EXPECTED_DEFAULT_VALUE,
				this.inputDimensions)).thenReturn(SAMPLE_STATEMENT_TEXT);

		when(this.mockElementFactory.createStatementFromText(eq(SAMPLE_STATEMENT_TEXT),
				eq(null))).thenReturn(mockDelegate);

		when(this.mockNodeFactory.getNode(mockDelegate)).thenReturn(mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		DeclarationStatementDelegate actualResult
				= this.builderUnderTest.buildEmptyArrayDeclaration(this.mockInputType,
				INPUT_NAME,
				this.inputDimensions);

		assertEquals(mockNode, actualResult, BAD_RETURN);
	}
}
