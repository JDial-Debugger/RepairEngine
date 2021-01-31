package ast.psi.node_builder;

import ast.interfaces.CodeBlockDelegate;
import ast.interfaces.MethodDelegate;
import ast.interfaces.ParameterListDelegate;
import ast.interfaces.TypeDelegate;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class MethodTests extends PsiNodeBuilderTestBase {

	private TypeDelegate inputType;
	private PsiType inputTypeDelegate;
	private String inputName;
	private ParameterListDelegate inputParamList;
	private PsiParameterList inputParamListDelegate;
	private CodeBlockDelegate inputBody;
	private PsiCodeBlock inputBodyDelegate;

	private PsiMethod mockDelegate;
	private MethodDelegate mockNode;

	private PsiParameterList mockDefaultParamList;
	private PsiCodeBlock mockDefaultBody;

	@Test
	void buildMethod() {

		this.setInputs();

		this.createMockNodes();

		this.stubDependencies();

		this.assertBuilderReturnsCorrectNodeFactoryResult();
	}

	private void setInputs() {

		this.setupInputType();

		this.inputName = "func1";

		this.setupInputParamList();

		this.setupInputBody();
	}

	private void setupInputType() {
		this.inputType = mock(TypeDelegate.class);
		this.inputTypeDelegate = mock(PsiType.class);
		when(this.mockExtractor.getDelegateType(this.inputType)).thenReturn(this.inputTypeDelegate);
	}

	private void setupInputParamList() {
		this.inputParamList = mock(ParameterListDelegate.class);
		this.inputParamListDelegate = mock(PsiParameterList.class);
		when(this.mockExtractor.getDelegateElement(PsiParameterList.class,
				this.inputParamList)).thenReturn(this.inputParamListDelegate);
	}

	private void setupInputBody() {
		this.inputBody = mock(CodeBlockDelegate.class);
		this.inputBodyDelegate = mock(PsiCodeBlock.class);
		when(this.mockExtractor.getDelegateElement(PsiCodeBlock.class, this.inputBody)).thenReturn(
				this.inputBodyDelegate);
	}

	private void createMockNodes() {
		this.mockDelegate = mock(PsiMethod.class);
		this.mockNode = mock(MethodDelegate.class);
	}

	private void stubDependencies() {
		when(this.mockElementFactory.createMethod(this.inputName,
				this.inputTypeDelegate)).thenReturn(this.mockDelegate);
		this.stubDefaultElements();

		when(this.mockNodeFactory.getNode(this.mockDelegate)).thenReturn(this.mockNode);
	}

	private void stubDefaultElements() {
		this.mockDefaultParamList = mock(PsiParameterList.class);
		when(this.mockDelegate.getParameterList()).thenReturn(this.mockDefaultParamList);

		this.mockDefaultBody = mock(PsiCodeBlock.class);
		when(this.mockDelegate.getBody()).thenReturn(this.mockDefaultBody);
	}

	private void assertBuilderReturnsCorrectNodeFactoryResult() {
		MethodDelegate actualResult = this.builderUnderTest.buildMethod(this.inputType,
				this.inputName,
				this.inputParamList,
				this.inputBody);

		assertEquals(this.mockNode, actualResult, BAD_RETURN);
		verify(this.mockDefaultParamList).replace(this.inputParamListDelegate);
		verify(this.mockDefaultBody).replace(this.inputBodyDelegate);
	}
}