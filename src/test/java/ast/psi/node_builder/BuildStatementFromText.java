package ast.psi.node_builder;

import ast.interfaces.ExpressionDelegate;
import ast.interfaces.StatementDelegate;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiStatement;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.nimbus.State;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BuildStatementFromText extends PsiNodeBuilderTestBase {
	private String inputText;

	private PsiStatement mockDelegate;
	private StatementDelegate mockNode;

	@Test
	void buildStatementFromText() {
		this.inputText = "int b = a + 4 - 3 * 2 + func()";

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void createMockNodes() {
		this.mockNode = mock(StatementDelegate.class);
		this.mockDelegate = mock(PsiStatement.class);
	}

	private void stubDependencies() {
		when(this.mockElementFactory.createStatementFromText(inputText,
				null)).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		StatementDelegate actualResult
				= this.builderUnderTest.buildStatementFromText(this.inputText);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
