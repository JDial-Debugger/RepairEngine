package ast.psi;

import ast.interfaces.Statement;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CodeBlockImplTests {

	private CodeBlockImpl codeBlockUnderTest;
	private PsiCodeBlock mockDelegate;
	private PsiElementExtractor mockExtractor;
	private DelegateStore mockDelegateStore;
	private Statement[] sampleStatements;
	private PsiStatement[] sampleStatementDelegates;

	private static final String NO_NEXT_ITEM = "Iterator did not have next item";
	private static final String UNEXPECTED_NEXT_ITEM = "Iterator.hasNext() did not return false";

	@BeforeEach
	public void setUp() {
		this.mockDelegate = mock(PsiCodeBlock.class);
		this.mockExtractor = mock(PsiElementExtractor.class);
		this.mockDelegateStore = mock(DelegateStore.class);
		NodeConfig<PsiCodeBlock> config = new NodeConfig<>(this.mockDelegate,
				this.mockExtractor,
				this.mockDelegateStore);
		this.codeBlockUnderTest = new CodeBlockImpl(config);
	}

	@Test
	public void iterator() {

		this.setInput();

		this.stubDelegateStore();

		Iterator<Statement> actualIter = this.codeBlockUnderTest.iterator();

		this.assertIteratesOverItemCount(actualIter, this.sampleStatementDelegates.length);
	}

	private void setInput() {
		this.sampleStatementDelegates = new PsiStatement[] {
				mock(PsiStatement.class), mock(PsiStatement.class), mock(PsiStatement.class),
		};
		this.sampleStatements = new Statement[] {
				mock(Statement.class), mock(Statement.class), mock(Statement.class),
		};
		when(this.mockDelegate.getStatements()).thenReturn(sampleStatementDelegates);
	}

	private void stubDelegateStore() {
		for (int i = 0; i < this.sampleStatements.length; ++i) {
			when(this.mockDelegateStore.getNodeFrom(this.sampleStatementDelegates[i])).thenReturn(
					this.sampleStatements[i]);
		}
	}

	private void assertIteratesOverItemCount(Iterator<Statement> iter, int count) {
		for (int i = 0; i < count; ++i) {
			assertTrue(iter.hasNext(), NO_NEXT_ITEM);
			assertEquals(this.sampleStatements[i], iter.next());
		}
		assertFalse(iter.hasNext(), UNEXPECTED_NEXT_ITEM);
	}
}
