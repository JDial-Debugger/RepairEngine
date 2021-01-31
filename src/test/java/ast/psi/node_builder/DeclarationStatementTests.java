package ast.psi.node_builder;

import ast.interfaces.DeclarationStatementDelegate;
import ast.interfaces.ExpressionDelegate;
import ast.interfaces.TypeDelegate;
import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeclarationStatementTests extends PsiNodeBuilderTestBase {

	private String inputName;
	private TypeDelegate inputType;
	private PsiType inputTypeDelegate;
	private ExpressionDelegate inputInitializer;
	private PsiExpression inputInitializerDelegate;

	private PsiDeclarationStatement mockDelegate;
	private DeclarationStatementDelegate mockNode;

	private static final String DEFAULT_TEXT = "int a = 0;";

	@Test
	void buildDeclarationStatement() {

		this.setInputs();

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		DeclarationStatementDelegate actualResult = this.builderUnderTest.buildDeclarationStatement(this.inputName,
				this.inputType,
				this.inputInitializer);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);

	}

	private void setInputs() {

		this.inputName = "var1";

		this.inputType = mock(TypeDelegate.class);
		this.inputTypeDelegate = mock(PsiType.class);
		when(this.mockExtractor.getDelegateType(this.inputType)).thenReturn(this.inputTypeDelegate);

		this.inputInitializer = mock(ExpressionDelegate.class);
		this.inputInitializerDelegate = mock(PsiExpression.class);
		when(this.mockExtractor.getDelegateElement(PsiExpression.class,
				this.inputInitializer)).thenReturn(this.inputInitializerDelegate);
	}

	private void createMockNodes() {
		this.mockNode = mock(DeclarationStatementDelegate.class);
		this.mockDelegate = mock(PsiDeclarationStatement.class);
	}

	private void stubDependencies() {
		when(this.mockElementFactory.createVariableDeclarationStatement(this.inputName,
				this.inputTypeDelegate,
				this.inputInitializerDelegate)).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}


	@Test
	void buildEmptyDeclarationStatement() {

		this.createMockNodes();

		this.stubDependenciesForEmptyStatement();

		this.assertBuilderReturnsCorrectNodeFactoryResultForEmptyStatement();
	}

	private void stubDependenciesForEmptyStatement() {
		when(this.mockElementFactory.createStatementFromText(DEFAULT_TEXT,
				null)).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResultForEmptyStatement() {
		DeclarationStatementDelegate actualResult
				= this.builderUnderTest.buildEmptyDeclarationStatement();

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
