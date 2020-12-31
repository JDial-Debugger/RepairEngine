package ast.psi;

import ast.interfaces.LiteralExpressionDelegate;
import com.intellij.psi.PsiLiteralExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PsiNodeBuilderBuildLiteralIntExpressionTest extends PsiNodeBuilderTestBase {

	@Test
	void buildLiteralIntExpression() {
		int sampleInput = 5;
		String expectedExpressionString = "5";
		//  TODO leftoff making this like buildIfStatement
		PsiLiteralExpression mockElementFactoryResult = mock(PsiLiteralExpression.class);
		when(this.mockElementFactory.createExpressionFromText(expectedExpressionString,
				null)).thenReturn(mockElementFactoryResult);

		LiteralExpressionDelegate expectedResult = mock(LiteralExpressionDelegate.class);
		when(this.mockNodeFactory.getNode(mockElementFactoryResult)).thenReturn(expectedResult);

		LiteralExpressionDelegate actualResult = this.builderUnderTest.buildLiteralIntExpression(
				sampleInput);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
	}
}
