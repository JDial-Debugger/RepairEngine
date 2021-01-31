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
	protected CommandProcessorDelegate mockProcessor;
	protected PsiElementExtractor mockExtractor;
	protected ArrayStringBuilder mockArrayStringBuilder;
	protected NodeFactory mockNodeFactory;

	protected static final String BAD_RETURN = "Did not return result from NodeFactory";

	@BeforeEach
	protected void setUp() {
		this.mockElementFactory = mock(PsiElementFactory.class);
		this.mockProcessor = mock(CommandProcessorDelegate.class);
		this.mockExtractor = mock(PsiElementExtractor.class);
		this.mockArrayStringBuilder = mock(ArrayStringBuilder.class);
		this.mockNodeFactory = mock(NodeFactory.class);
		this.builderUnderTest = new PsiNodeBuilder(this.mockElementFactory,
				this.mockProcessor,
				this.mockExtractor,
				this.mockArrayStringBuilder,
				this.mockNodeFactory);
	}

	@Test
	void buildMethod() {

		TypeDelegate mockInputReturnType = mock(TypeDelegate.class);
		String inputName = "func1";
		ParameterListDelegate mockInputParamList = mock(ParameterListDelegate.class);
		CodeBlockDelegate mockInputBody = mock(CodeBlockDelegate.class);

		PsiType mockTypeDelegate = mock(PsiType.class);
		when(this.mockExtractor.getDelegateType(mockInputReturnType)).thenReturn(mockTypeDelegate);

		PsiMethod mockMethodDelegate = mock(PsiMethod.class);
		when(this.mockElementFactory.createMethod(inputName, mockTypeDelegate)).thenReturn(
				mockMethodDelegate);

		PsiParameterList mockOriginalEmptyParamList = mock(PsiParameterList.class);
		when(mockMethodDelegate.getParameterList()).thenReturn(mockOriginalEmptyParamList);
		PsiParameterList mockInputParamListDelegate = mock(PsiParameterList.class);
		when(this.mockExtractor.getDelegateElement(PsiParameterList.class,
				mockInputParamList)).thenReturn(mockInputParamListDelegate);

		PsiCodeBlock mockOriginalEmptyBody = mock(PsiCodeBlock.class);
		when(mockMethodDelegate.getBody()).thenReturn(mockOriginalEmptyBody);
		PsiCodeBlock mockInputBodyDelegate = mock(PsiCodeBlock.class);
		when(this.mockExtractor.getDelegateElement(PsiCodeBlock.class, mockInputBody)).thenReturn(
				mockInputBodyDelegate);

		MethodDelegate expectedResult = mock(MethodDelegate.class);
		when(this.mockNodeFactory.getNode(mockMethodDelegate)).thenReturn(expectedResult);

		MethodDelegate actualResult = this.builderUnderTest.buildMethod(mockInputReturnType,
				inputName,
				mockInputParamList,
				mockInputBody);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
		verify(mockOriginalEmptyParamList).replace(mockInputParamListDelegate);
		verify(mockOriginalEmptyBody).replace(mockInputBodyDelegate);
	}

	@Test
	void buildMethodOnNullParamList() {

	}

	@Test
	void buildEmptyMethod() {
	}

	@Test
	void buildBinaryExpression() {
	}

	@Test
	void buildMethodCall() {
	}
}