package ast.psi.mocks;

import ast.interfaces.NodeDelegate;
import ast.psi.mocks.MockNode;
import com.intellij.psi.PsiElement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Used for mocking objects returned from PsiElement methods in a readable manner
 *
 * @param <TElement> - The Psi Element type to mock
 */
public class MockPsiElement<TElement extends PsiElement> {
	public TElement self;

	public MockPsiElement(Class<TElement> elementToMockType) {
		this.self = mock(elementToMockType);
	}

	public void verifyReplacedWith(PsiElement element) {
		verify(self).replace(element);
	}

	public void verifyReplacedWith(MockNode<? extends NodeDelegate, ? extends PsiElement> mockNode) {
		this.verifyReplacedWith(mockNode.delegate);
	}
}
