package ast.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PsiStatementDelegateTest {

	private PsiStatementDelegate statementUnderTest;
	private PsiStatement mockStatement;
	private PsiElement mockParent;

	@BeforeEach
	void setUp() {
		this.mockStatement = mock(PsiStatement.class);
		this.mockParent = mock(PsiElement.class);
		when(this.mockStatement.getParent()).thenReturn(mockParent);
	}

	@Test
	void addAfter() {
	}
}