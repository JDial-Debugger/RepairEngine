package ast.psi.node_builder;

import ast.interfaces.CodeBlockDelegate;
import ast.interfaces.StatementDelegate;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiStatement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CodeBlockTests extends PsiNodeBuilderTestBase {

	private static final int INPUT_STATEMENT_COUNT = 5;

	private List<StatementDelegate> inputNodes;
	private List<PsiStatement> inputDelegates;

	private PsiCodeBlock mockDelegate;
	private CodeBlockDelegate mockNode;

	@Test
	void buildCodeBlockFromStatements() {

		this.setBuilderInput();

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderCorrectlyConstructsBlock();
	}

	private void setBuilderInput() {
		this.inputNodes = new ArrayList<>();
		this.inputDelegates = new ArrayList<>();

		this.mockBodyStatements();
	}

	private void mockBodyStatements() {
		for (int i = 0; i < INPUT_STATEMENT_COUNT; ++i) {
			StatementDelegate curInputStatement = mock(StatementDelegate.class);
			this.inputNodes.add(curInputStatement);

			PsiStatement mockCurDelegate = mock(PsiStatement.class);
			this.inputDelegates.add(mockCurDelegate);

			when(this.mockExtractor.getDelegateElement(PsiStatement.class,
					curInputStatement)).thenReturn(mockCurDelegate);
		}
	}

	private void createMockNodes() {
		this.mockNode = mock(CodeBlockDelegate.class);
		this.mockDelegate = mock(PsiCodeBlock.class);
	}

	private void stubDependencies() {
		when(this.mockElementFactory.createCodeBlock()).thenReturn(this.mockDelegate);

		when(this.mockNodeFactory.getNode(mockDelegate)).thenReturn(this.mockNode);
	}

	private void assertBuilderCorrectlyConstructsBlock() {
		CodeBlockDelegate actualResult
				= this.builderUnderTest.buildCodeBlockFromStatements(this.inputNodes);

		this.verifyInputDelegatesAddedToResultDelegate();

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}

	private void verifyInputDelegatesAddedToResultDelegate() {
		for (PsiStatement delegate : this.inputDelegates) {
			verify(mockDelegate).add(delegate);
		}
	}

	@Test
	void buildEmptyCodeBlock() {

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		CodeBlockDelegate actualResult = this.builderUnderTest.buildEmptyCodeBlock();

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
	}
}
