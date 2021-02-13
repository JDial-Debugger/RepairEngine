package ast.psi.node_builder;

import ast.interfaces.*;
import com.intellij.psi.PsiAssignmentExpression;
import com.intellij.psi.PsiBinaryExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AssignExpressionTests extends PsiNodeBuilderTestBase {


	private Expression inputLeft;
	private AssignOperator inputOp;
	private Expression inputRight;

	private PsiAssignmentExpression mockDelegate;
	private AssignExpression mockNode;

	@Test
	void buildBinaryExpression() {

		this.setInputs();

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setInputs() {
		this.inputLeft = mock(Expression.class);
		String leftText = "var";
		when(this.inputLeft.toString()).thenReturn(leftText);

		this.inputOp = AssignOperator.SIMPLE;

		this.inputRight = mock(Expression.class);
		String rightText = "3";
		when(this.inputRight.toString()).thenReturn(rightText);
	}

	private void createMockNodes() {
		this.mockDelegate = mock(PsiAssignmentExpression.class);
		this.mockNode = mock(AssignExpression.class);
	}

	private void stubDependencies() {
		String expectedExpressionText = "var=3";
		when(this.mockElementFactory.createExpressionFromText(eq(expectedExpressionText), isNull()))
				.thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		AssignExpression actualResult = this.builderUnderTest.buildAssignExpression(
				this.inputLeft,
				this.inputOp,
				this.inputRight);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}

	@Test
	public void buildBinaryExpressionOnDefaultOp() {
		this.setInputs();

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResultOnDefaultOp();
	}

	private void assertBuilderReturnsCorrectNodeFactoryResultOnDefaultOp() {
		AssignExpression actualResult = this.builderUnderTest.buildAssignExpression(
				this.inputLeft,
				this.inputRight);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
