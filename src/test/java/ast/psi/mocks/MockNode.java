package ast.psi.mocks;

import ast.interfaces.NodeDelegate;
import ast.psi.PsiElementExtractor;
import com.intellij.psi.PsiElement;

import static org.mockito.Mockito.*;

public class MockNode<TNode extends NodeDelegate, TDelegate extends PsiElement> {
	public TNode self;
	public TDelegate delegate;

	public MockNode(
			PsiElementExtractor mockExtractor,
			Class<TNode> mockNodeType,
			Class<TDelegate> mockDelegateType) {
		this.self = mock(mockNodeType);
		this.delegate = mock(mockDelegateType);
		when(mockExtractor.getDelegateElement(mockDelegateType, this.self)).thenReturn(delegate);
	}
}
