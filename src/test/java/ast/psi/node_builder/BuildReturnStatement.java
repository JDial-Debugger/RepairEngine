package ast.psi.node_builder;

import ast.interfaces.ExpressionDelegate;
import ast.interfaces.StatementDelegate;
import com.intellij.psi.PsiStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildReturnStatement extends PsiNodeBuilderTestBase {

	private ExpressionDelegate inputExpression;
	private PsiStatement mockDelegate;
	private StatementDelegate mockNode;

	@Test
	void buildReturnStatement() {

		this.setBuilderInput();

		this.createNodeMocks();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setBuilderInput() {
		this.inputExpression = mock(ExpressionDelegate.class);

		String inputExpressionText = "a + 4";
		when(this.inputExpression.toString()).thenReturn(inputExpressionText);

	}

	private void createNodeMocks() {
		this.mockNode = mock(StatementDelegate.class);
		this.mockDelegate = mock(PsiStatement.class);
	}

	private void stubDependencies() {
		String expectedReturnStatementText = "return a + 4;";
		when(this.mockElementFactory.createStatementFromText(expectedReturnStatementText,
				null)).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		StatementDelegate actualResult = this.builderUnderTest.buildReturnStatement(
				this.inputExpression);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
