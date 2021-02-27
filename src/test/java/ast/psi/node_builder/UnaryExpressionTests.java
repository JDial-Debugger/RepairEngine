package ast.psi.node_builder;

import ast.interfaces.*;
import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiUnaryExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnaryExpressionTests extends PsiNodeBuilderTestBase {

	private Expression inputExpression;
	private UnaryOperator inputOp;

	private UnaryExpression mockNode;
	private PsiUnaryExpression mockDelegate;

	@Test
	void buildBinaryExpression() {

		this.setInputs();

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setInputs() {
		this.inputExpression = mock(Expression.class);
		String leftText = "var1";
		when(this.inputExpression.toString()).thenReturn(leftText);

		this.inputOp = UnaryOperator.LOGICAL_INVERSE;
	}

	private void createMockNodes() {
		this.mockDelegate = mock(PsiUnaryExpression.class);
		this.mockNode = mock(UnaryExpression.class);
	}

	private void stubDependencies() {
		String expectedExpressionText = "!(var1)";
		when(this.mockElementFactory.createExpressionFromText(eq(expectedExpressionText), isNull()))
				.thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		UnaryExpression actualResult = this.builderUnderTest.buildUnaryExpression(
				this.inputExpression,
				this.inputOp);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
