package ast.psi;

import com.intellij.psi.PsiElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PsiStatementDelegateTest {

	private StatementImpl statementUnderTest;
	private com.intellij.psi.PsiStatement mockStatement;
	private PsiElement mockParent;

	@BeforeEach
	void setUp() {
		this.mockStatement = mock(com.intellij.psi.PsiStatement.class);
		this.mockParent = mock(PsiElement.class);
		when(this.mockStatement.getParent()).thenReturn(mockParent);
	}

	@Test
	void addAfter() {
	}
}