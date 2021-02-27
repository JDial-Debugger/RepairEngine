package ast.psi.node_builder;

import ast.interfaces.ArrayAccessExpression;
import ast.interfaces.Expression;
import ast.interfaces.UnaryExpression;
import ast.interfaces.UnaryOperator;
import com.intellij.psi.PsiArrayAccessExpression;
import com.intellij.psi.PsiUnaryExpression;
import org.apache.batik.gvt.text.ArabicTextHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArrayAccessTests extends PsiNodeBuilderTestBase {

	private Expression inputArray;
	private Expression inputIndex;

	private PsiArrayAccessExpression mockDelegate;
	private ArrayAccessExpression mockNode;

	@Test
	void buildBinaryExpression() {

		this.setInputs();

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setInputs() {
		this.inputArray = mock(Expression.class);
		String arrayText = "arr1";
		when(this.inputArray.toString()).thenReturn(arrayText);

		this.inputIndex = mock(Expression.class);
		String indexText = "0";
		when(this.inputIndex.toString()).thenReturn(indexText);
	}

	private void createMockNodes() {
		this.mockDelegate = mock(PsiArrayAccessExpression.class);
		this.mockNode = mock(ArrayAccessExpression.class);
	}

	private void stubDependencies() {
		String expectedExpressionText = "arr1[0]";
		when(this.mockElementFactory.createExpressionFromText(eq(expectedExpressionText), isNull()))
				.thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		ArrayAccessExpression actualResult = this.builderUnderTest.buildArrayAccessExpression(
				this.inputArray,
				this.inputIndex);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}

}
