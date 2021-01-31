package ast.psi.node_builder;

import ast.interfaces.ExpressionDelegate;
import com.intellij.psi.PsiMethodCallExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MethodCallTests extends PsiNodeBuilderTestBase {

	private String inputName;
	private ExpressionDelegate inputParam1;
	private ExpressionDelegate inputParam2;

	private PsiMethodCallExpression mockDelegate;
	private ExpressionDelegate mockNode;

	@Test
	void buildMethodCall() {
		this.setInputs();

		this.createMockNodes();

		this.stubDependencies("func1(foo,bar)");

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setInputs() {
		this.inputName = "func1";

		this.inputParam1 = mock(ExpressionDelegate.class);
		String param1Text = "foo";
		when(this.inputParam1.toString()).thenReturn(param1Text);

		this.inputParam2 = mock(ExpressionDelegate.class);
		String param2Text = "bar";
		when(this.inputParam2.toString()).thenReturn(param2Text);
	}

	private void createMockNodes() {
		this.mockDelegate = mock(PsiMethodCallExpression.class);
		this.mockNode = mock(ExpressionDelegate.class);
	}

	private void stubDependencies(String expectedExpressionText) {
		when(this.mockElementFactory.createExpressionFromText(eq(expectedExpressionText), isNull()))
				.thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		ExpressionDelegate actualResult = this.builderUnderTest.buildMethodCall(this.inputName,
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
		ExpressionDelegate actualResult = this.builderUnderTest.buildMethodCall(this.inputName);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
