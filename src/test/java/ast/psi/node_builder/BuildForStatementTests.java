package ast.psi.node_builder;

import ast.interfaces.Expression;
import ast.interfaces.ForStatement;
import ast.interfaces.Statement;
import com.intellij.psi.PsiForStatement;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BuildForStatementTests extends PsiNodeBuilderTestBase {

	private Statement inputInit;
	private Expression inputCondition;
	private Statement inputUpdate;
	private Statement inputBody;

	private PsiForStatement mockDelegate;
	private ForStatement mockNode;

	private static final String EXPECTED_STATEMENT_TEXT
			= "for (int i = 0; i < 5; ++i){\ncurIdx = i;\n}";
	private static final String EXPECTED_NULL_STATEMENT_TEXT
			= "for ( ; ; );";


	@Test
	public void buildForStatement() {
		this.setBuilderInput();

		this.createMockNodes();

		this.stubDependencies(EXPECTED_STATEMENT_TEXT);

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setBuilderInput() {
		this.inputInit = mock(Statement.class);
		when(this.inputInit.toString()).thenReturn("int i = 0;");

		this.inputCondition = mock(Expression.class);
		when(this.inputCondition.toString()).thenReturn("i < 5");

		this.inputUpdate = mock(Statement.class);
		when(this.inputUpdate.toString()).thenReturn("++i;");

		this.inputBody = mock(Statement.class);
		when(this.inputBody.toString()).thenReturn("{\ncurIdx = i;\n}");
	}

	private void createMockNodes() {
		this.mockDelegate = mock(PsiForStatement.class);
		this.mockNode = mock(ForStatement.class);
	}

	private void stubDependencies(String expectedText) {
		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
		when(this.mockElementFactory.createStatementFromText(expectedText, null))
				.thenReturn(this.mockDelegate);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		ForStatement actualResult = this.builderUnderTest.buildForStatement(
				this.inputInit,
				this.inputCondition,
				this.inputUpdate,
				this.inputBody);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}

	@Test
	public void BuildForStatementOnNullInputs() {

		this.createMockNodes();

		this.stubDependencies(EXPECTED_NULL_STATEMENT_TEXT);

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}
}
