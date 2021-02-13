package ast.psi.node_builder;

import ast.interfaces.*;
import ast.psi.*;
import ast.psi.factory.ArrayStringBuilder;
import ast.psi.mocks.MockNode;
import ast.psi.mocks.MockPsiElement;
import ast.psi.mocks.MockPsiIfStatement;
import com.intellij.psi.*;
import intellij.CommandProcessorDelegate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PsiNodeBuilderTestBase {

	protected PsiNodeBuilder builderUnderTest;
	//  Mocks
	protected PsiElementFactory mockElementFactory;
	protected PsiElementExtractor mockExtractor;
	protected ArrayStringBuilder mockArrayStringBuilder;
	protected NodeFactory mockNodeFactory;

	protected static final String BAD_RETURN = "Did not return result from NodeFactory";

	@BeforeEach
	protected void setUp() {
		this.mockElementFactory = mock(PsiElementFactory.class);
		this.mockExtractor = mock(PsiElementExtractor.class);
		this.mockArrayStringBuilder = mock(ArrayStringBuilder.class);
		this.mockNodeFactory = mock(NodeFactory.class);
		this.builderUnderTest = new PsiNodeBuilder(this.mockElementFactory,
				this.mockExtractor,
				this.mockArrayStringBuilder,
				this.mockNodeFactory);
	}
}