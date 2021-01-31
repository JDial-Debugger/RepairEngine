package ast.psi.node_builder;

import ast.interfaces.Expression;
import com.intellij.psi.PsiMethodCallExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MethodCallTests extends PsiNodeBuilderTestBase {

	private String inputName;
	private Expression inputParam1;
	private Expression inputParam2;

	private PsiMethodCallExpression mockDelegate;
	private Expression mockNode;

	@Test
	void buildMethodCall() {
		this.setInputs();

		this.createMockNodes();

		this.stubDependencies("func1(foo,bar)");

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setInputs() {
		this.inputName = "func1";

		this.inputParam1 = mock(Expression.class);
		String param1Text = "foo";
		when(this.inputParam1.toString()).thenReturn(param1Text);

		this.inputParam2 = mock(Expression.class);
		String param2Text = "bar";
		when(this.inputParam2.toString()).thenReturn(param2Text);
	}

	private void createMockNodes() {
		this.mockDelegate = mock(PsiMethodCallExpression.class);
		this.mockNode = mock(Expression.class);
	}

	private void stubDependencies(String expectedExpressionText) {
		when(this.mockElementFactory.createExpressionFromText(eq(expectedExpressionText), isNull()))
				.thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		Expression actualResult = this.builderUnderTest.buildMethodCall(this.inputName,
				this.inputParam1,
				this.inputParam2);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}

	@Test
	void buildMethodCallOnNoParams() {
		this.inputName = "func1";

		this.createMockNodes();

		this.stubDependencies("func1()");

		this.assertBuilderReturnsCorrectNodeFactoryResultOnNoParams();
	}

	private void assertBuilderReturnsCorrectNodeFactoryResultOnNoParams() {
		Expression actualResult = this.builderUnderTest.buildMethodCall(this.inputName);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
