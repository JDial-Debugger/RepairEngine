package ast.psi.mocks;

import ast.interfaces.Node;
import ast.psi.NodeFactory;
import ast.psi.PsiElementExtractor;
import com.intellij.psi.PsiElement;

import static org.mockito.Mockito.*;

/**
 * Used to link a mocked Node with the underlying mocked PsiElement from the IntelliJ sdk. Provides
 * some utility for mocking the relationship between Nodes and their wrapped PsiElement.
 *
 * Due to mockito not working if you pass in generically typed objects as matchers, this class is
 * unused/useless. Keeping it here in case Mockito fixes this issue.
 *
 * @param <TNode>     The type of the Node to mock
 * @param <TDelegate> the type of the Delegate/PsiElement to mock
 */
public class MockNode<TNode extends Node, TDelegate extends PsiElement> {

	public TNode self;
	public TDelegate delegate;

	/**
	 * @param mockExtractor    mocks this to correctly extract the mocked delegate from mocked node
	 * @param mockNodeType
	 * @param mockDelegateType
	 * @param nodeFactory      mock this to return the mocked node when passed the mocked delegate
	 */
	public MockNode(
			PsiElementExtractor mockExtractor,
			Class<TNode> mockNodeType,
			Class<TDelegate> mockDelegateType,
			NodeFactory nodeFactory) {
		this(mockExtractor, mockNodeType, mockDelegateType);
		when(nodeFactory.getNode(this.delegate))
				.thenReturn(this.self);
	}

	public MockNode(
			PsiElementExtractor mockExtractor,
			Class<TNode> mockNodeType,
			Class<TDelegate> mockDelegateType) {
		this(mockNodeType, mockDelegateType);
		when(mockExtractor.getDelegateElement(mockDelegateType, this.self)).thenReturn(delegate);
	}

	public MockNode(
			Class<TNode> mockNodeType, Class<TDelegate> mockDelegateType) {
		this.self = mock(mockNodeType);
		this.delegate = mock(mockDelegateType);
	}

}
