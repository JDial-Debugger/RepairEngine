package ast.psi.node_builder;

import ast.interfaces.BinaryExpressionDelegate;
import ast.interfaces.BinaryOperator;
import ast.interfaces.ExpressionDelegate;
import com.intellij.psi.PsiBinaryExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BinaryExpressionTests extends PsiNodeBuilderTestBase {

	private ExpressionDelegate inputLeft;
	private BinaryOperator inputOp;
	private ExpressionDelegate inputRight;

	private PsiBinaryExpression mockDelegate;
	private BinaryExpressionDelegate mockNode;

	@Test
	void buildBinaryExpression() {

		this.setInputs();

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setInputs() {
		this.inputLeft = mock(ExpressionDelegate.class);
		String leftText = "var1";
		when(this.inputLeft.toString()).thenReturn(leftText);

		this.inputOp = BinaryOperator.ADD;

		this.inputRight = mock(ExpressionDelegate.class);
		String rightText = "5";
		when(this.inputRight.toString()).thenReturn(rightText);
	}

	private void createMockNodes() {
		this.mockDelegate = mock(PsiBinaryExpression.class);
		this.mockNode = mock(BinaryExpressionDelegate.class);
	}

	private void stubDependencies() {
		String expectedExpressionText = "var1+5";
		when(this.mockElementFactory.createExpressionFromText(eq(expectedExpressionText), isNull()))
				.thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		BinaryExpressionDelegate actualResult = this.builderUnderTest.buildBinaryExpression(this.inputLeft, this.inputOp, this.inputRight);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
