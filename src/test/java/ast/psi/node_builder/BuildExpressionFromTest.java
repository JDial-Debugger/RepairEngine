package ast.psi.node_builder;

import ast.interfaces.ExpressionDelegate;
import com.intellij.psi.PsiExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildExpressionFromTest extends PsiNodeBuilderTestBase {

	private String inputText;

	private PsiExpression mockDelegate;
	private ExpressionDelegate mockNode;

	@Test
	void buildExpressionFromText() {

		this.inputText = "a + 4 - 3 * 2 + func()";

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void createMockNodes() {
		this.mockNode = mock(ExpressionDelegate.class);
		this.mockDelegate = mock(PsiExpression.class);
	}

	private void stubDependencies() {
		when(this.mockElementFactory.createExpressionFromText(this.inputText,
				null)).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		ExpressionDelegate actualResult = this.builderUnderTest.buildExpressionFromText(inputText);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
