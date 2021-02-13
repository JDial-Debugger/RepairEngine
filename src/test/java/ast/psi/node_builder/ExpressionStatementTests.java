package ast.psi.node_builder;

import ast.interfaces.Expression;
import ast.interfaces.ExpressionStatement;
import ast.interfaces.Statement;
import com.intellij.psi.PsiExpressionStatement;
import com.intellij.psi.PsiStatement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ExpressionStatementTests extends PsiNodeBuilderTestBase {
	private Expression inputExpression;

	private PsiExpressionStatement mockDelegate;
	private ExpressionStatement mockNode;

	@Test
	void buildReturnStatement() {

		this.setBuilderInput();

		this.createNodeMocks();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setBuilderInput() {
		this.inputExpression = mock(Expression.class);

		String inputExpressionText = "a = 4";
		when(this.inputExpression.toString()).thenReturn(inputExpressionText);
	}

	private void createNodeMocks() {
		this.mockNode = mock(ExpressionStatement.class);
		this.mockDelegate = mock(PsiExpressionStatement.class);
	}

	private void stubDependencies() {
		String expectedReturnStatementText = "a = 4;";
		when(this.mockElementFactory.createStatementFromText(eq(expectedReturnStatementText),
				isNull())).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		ExpressionStatement actualResult
				= this.builderUnderTest.buildExpressionStatement(this.inputExpression);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
