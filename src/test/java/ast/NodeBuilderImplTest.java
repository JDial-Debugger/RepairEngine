package ast;

import ast.interfaces.DeclarationStatement;
import ast.psi.PsiNodeBuilder;
import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiElementFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class NodeBuilderImplTest {

	private PsiNodeBuilder factoryToTest;
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
		DeclarationStatement result = factoryToTest.buildEmptyDeclarationStatement();

	}
}