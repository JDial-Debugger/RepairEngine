package ast.psi.mocks;

import ast.interfaces.CodeBlock;
import ast.interfaces.Expression;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiIfStatement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockPsiIfStatement extends MockPsiElement<PsiIfStatement> {
	public MockPsiExpression condition;
	public MockPsiStatement thenBranch;
	public MockPsiStatement elseBranch;

	public MockPsiIfStatement() {
		this(true, true, true);
	}

	public MockPsiIfStatement(
			boolean shouldMockCondition,
			boolean shouldMockThenBranch,
			boolean shouldMockElseBranch) {
		super(PsiIfStatement.class);
		if (shouldMockCondition) {
			this.setCondition();
		}
		if (shouldMockThenBranch) {
			this.setThenBranch();
		}
		if (shouldMockElseBranch) {
			this.setElseBranch();
		}
	}

	private void setCondition() {
		this.condition = new MockPsiExpression();
		when(this.self.getCondition()).thenReturn(this.condition.self);
	}

	private void setThenBranch() {
		this.thenBranch = new MockPsiStatement();
		when(this.self.getThenBranch()).thenReturn(this.thenBranch.self);
	}

	private void setElseBranch() {
		this.elseBranch = new MockPsiStatement();
		when(this.self.getElseBranch()).thenReturn(this.elseBranch.self);
	}

	public void verifyComponentsAreReplacedWith(
			MockNode<Expression, PsiExpression> newCondition,
			MockNode<CodeBlock, PsiCodeBlock> newThenBranch,
			MockNode<CodeBlock, PsiCodeBlock> newElseBranch) {
		this.verifyComponentsAreReplacedWith(newCondition, newThenBranch);
		this.elseBranch.verifyReplacedWith(newElseBranch);
	}

	public void verifyComponentsAreReplacedWith(
			MockNode<Expression, PsiExpression> newCondition,
			MockNode<CodeBlock, PsiCodeBlock> newThenBranch) {
		this.condition.verifyReplacedWith(newCondition);
		this.thenBranch.verifyReplacedWith(newThenBranch);
	}
}
