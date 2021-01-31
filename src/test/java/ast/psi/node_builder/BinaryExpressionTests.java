package ast.psi.node_builder;

import ast.interfaces.BinaryExpression;
import ast.interfaces.BinaryOperator;
import ast.interfaces.Expression;
import com.intellij.psi.PsiBinaryExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BinaryExpressionTests extends PsiNodeBuilderTestBase {

	private Expression inputLeft;
	private BinaryOperator inputOp;
	private Expression inputRight;

	private PsiBinaryExpression mockDelegate;
	private BinaryExpression mockNode;

	@Test
	void buildBinaryExpression() {

		this.setInputs();

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setInputs() {
		this.inputLeft = mock(Expression.class);
		String leftText = "var1";
		when(this.inputLeft.toString()).thenReturn(leftText);

		this.inputOp = BinaryOperator.ADD;

		this.inputRight = mock(Expression.class);
		String rightText = "5";
		when(this.inputRight.toString()).thenReturn(rightText);
	}

	private void createMockNodes() {
		this.mockDelegate = mock(PsiBinaryExpression.class);
		this.mockNode = mock(BinaryExpression.class);
	}

	private void stubDependencies() {
		String expectedExpressionText = "var1+5";
		when(this.mockElementFactory.createExpressionFromText(eq(expectedExpressionText), isNull()))
				.thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		BinaryExpression actualResult = this.builderUnderTest.buildBinaryExpression(this.inputLeft, this.inputOp, this.inputRight);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
