package ast;

import ast.interfaces.DeclarationStatementDelegate;
import ast.psi.PsiNodeFactory;
import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiElementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class NodeFactoryImplTest {

	private PsiNodeFactory factoryToTest;
	private PsiElementFactory mockWrappedFactory;

	@BeforeEach
	public void SetUp() {
		this.mockWrappedFactory = mock(PsiElementFactory.class);
	}
	@Test
	void getEmptyDeclarationStatement() {
		PsiDeclarationStatement mockDeclStmt = mock(PsiDeclarationStatement.class);
		when(mockWrappedFactory.createStatementFromText(eq("int a = 0;"), eq(null)))
				.thenReturn(mockDeclStmt);
		DeclarationStatementDelegate result = factoryToTest.getEmptyDeclarationStatement();

	}
}