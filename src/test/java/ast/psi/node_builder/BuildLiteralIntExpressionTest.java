package ast.psi.node_builder;

import ast.interfaces.LiteralExpressionDelegate;
import ast.psi.node_builder.PsiNodeBuilderTestBase;
import com.intellij.psi.PsiLiteralExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BuildLiteralIntExpressionTest extends PsiNodeBuilderTestBase {

	private LiteralExpressionDelegate mockNode;
	private PsiLiteralExpression mockDelegate;

	private static final int INPUT_EXPRESSION_CONTENTS = 5;
	private static final String EXPECTED_EXPRESSION_CONTENTS = "5";

	@Test
	void buildLiteralIntExpression() {

		this.createNodeMocks();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void createNodeMocks() {
		this.mockNode = mock(LiteralExpressionDelegate.class);
		this.mockDelegate = mock(PsiLiteralExpression.class);
	}

	private void stubDependencies() {
		when(this.mockElementFactory.createExpressionFromText(eq(EXPECTED_EXPRESSION_CONTENTS),
				isNull())).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		LiteralExpressionDelegate actualResult = this.builderUnderTest.buildLiteralIntExpression(
				INPUT_EXPRESSION_CONTENTS);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
