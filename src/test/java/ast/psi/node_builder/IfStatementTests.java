package ast.psi.node_builder;

import ast.interfaces.CodeBlockDelegate;
import ast.interfaces.ExpressionDelegate;
import ast.interfaces.IfStatementDelegate;
import ast.interfaces.NodeDelegate;
import ast.psi.mocks.MockNode;
import ast.psi.mocks.MockPsiIfStatement;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IfStatementTests extends PsiNodeBuilderTestBase {
	private MockNode<ExpressionDelegate, PsiExpression> inputCondition;
	private MockNode<CodeBlockDelegate, PsiCodeBlock> inputThenBranch;
	private MockNode<CodeBlockDelegate, PsiCodeBlock> inputElseBranch;

	private MockPsiIfStatement mockDelegate;
	private IfStatementDelegate mockNode;

	private static final String DEFAULT_TEXT = "if(true){}";

	@Test
	void buildIfStatementOnElseBranch() {

		this.setBuilderInput(true);

		this.createMockNodes(true);

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setBuilderInput(boolean includeElseBranch) {
		this.inputCondition = new MockNode<>(ExpressionDelegate.class, PsiExpression.class);
		when(this.mockExtractor.getDelegateElement(PsiExpression.class,
				this.inputCondition.self)).thenReturn(this.inputCondition.delegate);

		this.inputThenBranch = new MockNode<>(CodeBlockDelegate.class, PsiCodeBlock.class);
		when(this.mockExtractor.getDelegateElement(PsiCodeBlock.class,
				this.inputThenBranch.self)).thenReturn(this.inputThenBranch.delegate);

		if (includeElseBranch) {
			this.inputElseBranch = new MockNode<>(CodeBlockDelegate.class, PsiCodeBlock.class);
			when(this.mockExtractor.getDelegateElement(PsiCodeBlock.class,
					this.inputElseBranch.self)).thenReturn(this.inputElseBranch.delegate);
		}
	}

	private void createMockNodes(boolean includeElseBranch) {
		this.mockNode = mock(IfStatementDelegate.class);
		this.mockDelegate = new MockPsiIfStatement(true, true, includeElseBranch);
	}

	private void stubDependencies() {
		when(this.mockElementFactory.createStatementFromText(DEFAULT_TEXT,
				null)).thenReturn(this.mockDelegate.self);

		when(this.mockNodeFactory.getNode(this.mockDelegate.self)).thenReturn(this.mockNode);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		IfStatementDelegate actualResult
				= this.builderUnderTest.buildIfStatement(this.inputCondition.self,
				this.inputThenBranch.self,
				this.inputElseBranch.self);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
		this.mockDelegate.verifyComponentsAreReplacedWith(this.inputCondition,
				this.inputThenBranch,
				this.inputElseBranch);
	}

	@Test
	void buildIfStatement() {

		this.setBuilderInput(false);

		this.createMockNodes(false);

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResultWithoutElseBranch();
	}

	private void assertBuilderReturnsCorrectNodeFactoryResultWithoutElseBranch() {
		IfStatementDelegate actualResult
				= this.builderUnderTest.buildIfStatement(this.inputCondition.self,
				this.inputThenBranch.self);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
		this.mockDelegate.verifyComponentsAreReplacedWith(this.inputCondition,
				this.inputThenBranch);

	}
}