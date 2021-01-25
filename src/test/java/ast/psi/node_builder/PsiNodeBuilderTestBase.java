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

	//  TODO
	@Test
	void getEmptyArrayDeclaration() {

	}

	@Test
	void buildEmptyArrayDeclaration() {
		TypeDelegate sampleType = mock(TypeDelegate.class);
		when(sampleType.asEnum()).thenReturn(Type.INT);

		String sampleName = "array";
		Integer[] sampleDimensions = { 2, 2, 1 };
		String expectedDefaultValue = "0";
		String statementText = "int[2][2][1] array = {{{0}, {0}}};";

		this.mockLiteral(expectedDefaultValue);

		when(this.mockArrayStringBuilder.buildArrayDeclarationStatement(sampleType,
				sampleName,
				expectedDefaultValue,
				sampleDimensions)).thenReturn(statementText);

		PsiDeclarationStatement mockElementFactoryResult = mock(PsiDeclarationStatement.class);
		when(this.mockElementFactory.createStatementFromText(eq(statementText),
				eq(null))).thenReturn(mockElementFactoryResult);

		DeclarationStatementDelegate expectedResult = mock(DeclarationStatementDelegate.class);
		when(this.mockNodeFactory.getNode(mockElementFactoryResult)).thenReturn(expectedResult);

		DeclarationStatementDelegate actualResult
				= this.builderUnderTest.buildEmptyArrayDeclaration(sampleType,
				sampleName,
				sampleDimensions);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
	}

	private void mockLiteral(String literalValue) {
		PsiLiteralExpression mockLiteralDelegate = mock(PsiLiteralExpression.class);
		when(this.mockElementFactory.createExpressionFromText(literalValue, null)).thenReturn(
				mockLiteralDelegate);

		LiteralExpressionDelegate mockLiteral = mock(LiteralExpressionDelegate.class);
		when(this.mockNodeFactory.getNode(mockLiteralDelegate)).thenReturn(mockLiteral);
		when(mockLiteral.toString()).thenReturn("0");
	}

	@Test
	void buildEmptyArrayDeclarationOnBadDimensions() {
		TypeDelegate sampleType = mock(TypeDelegate.class);
		when(sampleType.asEnum()).thenReturn(Type.INT);
		String sampleName = "array";
		Integer[] sampleDimensions = { 2, 2, 0, 3 };
		when(this.mockArrayStringBuilder.buildArrayDeclarationStatement(any(), any(), any(), any()))
				.thenThrow(new InvalidDimensionSizeException(0, 2));

		PsiLiteralExpression mockLiteralDelegate = mock(PsiLiteralExpression.class);
		when(this.mockElementFactory.createExpressionFromText(any(), eq(null))).thenReturn(
				mockLiteralDelegate);

		LiteralExpressionDelegate mockLiteral = mock(LiteralExpressionDelegate.class);
		when(this.mockNodeFactory.getNode(mockLiteralDelegate)).thenReturn(mockLiteral);

		assertThrows(InvalidDimensionSizeException.class,
				() -> this.builderUnderTest.buildEmptyArrayDeclaration(sampleType,
						sampleName,
						sampleDimensions));

	}

	@Test
	void buildDefaultLiteralExpressionForByte() {
		this.buildDefaultLiteralExpressionFor(Type.BYTE, "0");
	}

	private void buildDefaultLiteralExpressionFor(Type typeToTest, String expectedDefaultLiteral) {

		TypeDelegate mockInput = mock(TypeDelegate.class);
		when(mockInput.asEnum()).thenReturn(typeToTest);

		PsiLiteralExpression mockElementFactoryResult = mock(PsiLiteralExpression.class);
		when(this.mockElementFactory.createExpressionFromText(expectedDefaultLiteral,
				null)).thenReturn(mockElementFactoryResult);

		LiteralExpressionDelegate expectedResult = mock(LiteralExpressionDelegate.class);
		when(this.mockNodeFactory.getNode(mockElementFactoryResult)).thenReturn(expectedResult);

		LiteralExpressionDelegate actualResult
				= this.builderUnderTest.buildDefaultLiteralExpressionFor(mockInput);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
	}

	@Test
	void buildDefaultLiteralExpressionForBoolean() {
		this.buildDefaultLiteralExpressionFor(Type.BOOLEAN, "false");
	}

	@Test
	void buildDefaultLiteralExpressionForChar() {
		this.buildDefaultLiteralExpressionFor(Type.CHAR, "\'\\u0000\'");
	}

	@Test
	void buildDefaultLiteralExpressionForDouble() {
		this.buildDefaultLiteralExpressionFor(Type.DOUBLE, "0.0d");
	}

	@Test
	void buildDefaultLiteralExpressionForFloat() {
		this.buildDefaultLiteralExpressionFor(Type.FLOAT, "0.0f");
	}

	@Test
	void buildDefaultLiteralExpressionForInt() {
		this.buildDefaultLiteralExpressionFor(Type.INT, "0");
	}

	@Test
	void buildDefaultLiteralExpressionForLong() {
		this.buildDefaultLiteralExpressionFor(Type.LONG, "0.0L");
	}

	@Test
	void buildDefaultLiteralExpressionForShort() {
		this.buildDefaultLiteralExpressionFor(Type.SHORT, "0");
	}

	@Test
	void buildDefaultLiteralExpressionForVoid() {
		this.buildDefaultLiteralExpressionForBadType(Type.VOID, Type.VOID.toString());
	}

	@Test
	void buildDefaultLiteralExpressionForNullEnum() {
		this.buildDefaultLiteralExpressionForBadType(Type.NULL, Type.NULL.toString());
	}

	@Test
	void buildDefaultLiteralExpressionForNull() {
		this.buildDefaultLiteralExpressionForBadType(null, "null");
	}

	private void buildDefaultLiteralExpressionForBadType(Type badType, String enumAsString) {
		TypeDelegate mockInput = mock(TypeDelegate.class);
		when(mockInput.asEnum()).thenReturn(badType);

		InvalidTypeException ex = assertThrows(InvalidTypeException.class,
				() -> this.builderUnderTest.buildDefaultLiteralExpressionFor(mockInput));

		String expectedMessage = "Provided type " + enumAsString + " does not have a default value";
		assertEquals(expectedMessage, ex.getMessage(), "Expected error message was incorrect");
	}

	@Test
	void buildReturnStatement() {
		ExpressionDelegate mockExpressionToReturn = mock(ExpressionDelegate.class);
		String expressionToReturnText = "a + 4";
		when(mockExpressionToReturn.toString()).thenReturn(expressionToReturnText);

		PsiStatement mockElementFactoryResult = mock(PsiStatement.class);
		String expectedReturnStatementText = "return a + 4;";
		when(this.mockElementFactory.createStatementFromText(expectedReturnStatementText,
				null)).thenReturn(mockElementFactoryResult);

		StatementDelegate expectedResult = mock(StatementDelegate.class);
		when(this.mockNodeFactory.getNode(mockElementFactoryResult)).thenReturn(expectedResult);

		StatementDelegate actualResult = this.builderUnderTest.buildReturnStatement(
				mockExpressionToReturn);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
	}

	@Test
	void buildCodeBlockFromStatements() {
		List<StatementDelegate> mockBodyStatements = new ArrayList<>();
		List<PsiStatement> mockBodyDelegates = new ArrayList<>();

		this.mockBodyStatements(5, mockBodyStatements, mockBodyDelegates);

		PsiCodeBlock mockDelegate = mock(PsiCodeBlock.class);
		when(this.mockElementFactory.createCodeBlock()).thenReturn(mockDelegate);

		CodeBlockDelegate expectedResult = mock(CodeBlockDelegate.class);
		when(this.mockNodeFactory.getNode(mockDelegate)).thenReturn(expectedResult);

		CodeBlockDelegate actualResult = this.builderUnderTest.buildCodeBlockFromStatements(
				mockBodyStatements);

		for (PsiStatement delegate : mockBodyDelegates) {
			verify(mockDelegate).add(delegate);
		}
		assertEquals(expectedResult, actualResult, BAD_RETURN);

	}

	private void mockBodyStatements(
			int statementCount,
			List<StatementDelegate> statements,
			List<PsiStatement> statementDelegates) {

		for (int i = 0; i < statementCount; ++i) {
			StatementDelegate curInputStatement = mock(StatementDelegate.class);
			statements.add(curInputStatement);

			PsiStatement mockCurDelegate = mock(PsiStatement.class);
			statementDelegates.add(mockCurDelegate);

			when(this.mockExtractor.getDelegateElement(PsiStatement.class,
					curInputStatement)).thenReturn(mockCurDelegate);
		}
	}

	@Test
	void buildEmptyCodeBlock() {
		PsiCodeBlock mockElementFactoryResult = mock(PsiCodeBlock.class);
		when(this.mockElementFactory.createCodeBlock()).thenReturn(mockElementFactoryResult);

		CodeBlockDelegate expectedResult = mock(CodeBlockDelegate.class);
		when(this.mockNodeFactory.getNode(mockElementFactoryResult)).thenReturn(expectedResult);

		CodeBlockDelegate actualResult = this.builderUnderTest.buildEmptyCodeBlock();

		assertEquals(expectedResult, actualResult, BAD_RETURN);
	}


	@Test
	void buildExpressionFromText() {
		String sampleText = "a + 4 - 3 * 2 + func()";

		PsiExpression mockElementFactoryResult = mock(PsiExpression.class);
		when(this.mockElementFactory.createExpressionFromText(sampleText, null)).thenReturn(
				mockElementFactoryResult);

		ExpressionDelegate expectedResult = mock(ExpressionDelegate.class);
		when(this.mockNodeFactory.getNode(mockElementFactoryResult)).thenReturn(expectedResult);

		ExpressionDelegate actualResult = this.builderUnderTest.buildExpressionFromText(sampleText);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
	}

	@Test
	void buildStatementFromText() {
		String sampleText = "int b = a + 4 - 3 * 2 + func()";

		PsiStatement mockElementFactoryResult = mock(PsiStatement.class);
		when(this.mockElementFactory.createStatementFromText(sampleText, null)).thenReturn(
				mockElementFactoryResult);

		StatementDelegate expectedResult = mock(StatementDelegate.class);
		when(this.mockNodeFactory.getNode(mockElementFactoryResult)).thenReturn(expectedResult);

		StatementDelegate actualResult = this.builderUnderTest.buildStatementFromText(sampleText);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
	}

	@Test
	void buildEmptyDeclarationStatement() {

		PsiDeclarationStatement mockElementFactoryResult = mock(PsiDeclarationStatement.class);
		String defaultText = "int a = 0;";
		when(this.mockElementFactory.createStatementFromText(defaultText, null)).thenReturn(
				mockElementFactoryResult);

		DeclarationStatementDelegate expectedResult = mock(DeclarationStatementDelegate.class);
		when(this.mockNodeFactory.getNode(mockElementFactoryResult)).thenReturn(expectedResult);

		DeclarationStatementDelegate actualResult
				= this.builderUnderTest.buildEmptyDeclarationStatement();

		assertEquals(expectedResult, actualResult, BAD_RETURN);
	}

	@Test
	void buildDeclarationStatement() {
		String sampleName = "var1";
		TypeDelegate mockType = mock(TypeDelegate.class);
		ExpressionDelegate mockInitializer = mock(ExpressionDelegate.class);

		PsiType mockDelegateType = mock(PsiType.class);
		when(this.mockExtractor.getDelegateType(mockType)).thenReturn(mockDelegateType);

		PsiExpression mockDelegateInitExpression = mock(PsiExpression.class);
		when(this.mockExtractor.getDelegateElement(PsiExpression.class,
				mockInitializer)).thenReturn(mockDelegateInitExpression);

		PsiDeclarationStatement mockElementFactoryResult = mock(PsiDeclarationStatement.class);
		when(this.mockElementFactory.createVariableDeclarationStatement(sampleName,
				mockDelegateType,
				mockDelegateInitExpression)).thenReturn(mockElementFactoryResult);

		DeclarationStatementDelegate expectedResult = mock(DeclarationStatementDelegate.class);
		when(this.mockNodeFactory.getNode(mockElementFactoryResult)).thenReturn(expectedResult);

		DeclarationStatementDelegate actualResult = this.builderUnderTest.buildDeclarationStatement(sampleName,
				mockType,
				mockInitializer);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
	}

	@Test
	void buildIfStatement() {

		MockNode<ExpressionDelegate, PsiExpression> mockInputCondition
				= new MockNode<>(this.mockExtractor, ExpressionDelegate.class, PsiExpression.class);

		MockNode<CodeBlockDelegate, PsiCodeBlock> mockInputThenBranch
				= new MockNode<>(this.mockExtractor, CodeBlockDelegate.class, PsiCodeBlock.class);

		MockPsiIfStatement mockIfStatementDelegate = new MockPsiIfStatement(true, true, false);

		String defaultText = "if(true){}";
		when(this.mockElementFactory.createStatementFromText(defaultText, null)).thenReturn(
				mockIfStatementDelegate.self);

		IfStatementDelegate expectedResult = mock(IfStatementDelegate.class);
		when(this.mockNodeFactory.getNode(mockIfStatementDelegate.self)).thenReturn(expectedResult);

		IfStatementDelegate actualResult
				= this.builderUnderTest.buildIfStatement(mockInputCondition.self,
				mockInputThenBranch.self);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
		mockIfStatementDelegate.verifyComponentsAreReplacedWith(mockInputCondition,
				mockInputThenBranch);
	}

	@Test
	void buildIfStatementOnElseBranch() {
		MockNode<ExpressionDelegate, PsiExpression> mockInputCondition
				= new MockNode<>(this.mockExtractor, ExpressionDelegate.class, PsiExpression.class);

		MockNode<CodeBlockDelegate, PsiCodeBlock> mockInputThenBranch
				= new MockNode<>(this.mockExtractor, CodeBlockDelegate.class, PsiCodeBlock.class);

		MockNode<CodeBlockDelegate, PsiCodeBlock> mockInputElseBranch
				= new MockNode<>(this.mockExtractor, CodeBlockDelegate.class, PsiCodeBlock.class);

		MockPsiIfStatement mockIfStatementDelegate = new MockPsiIfStatement();

		String defaultText = "if(true){}";
		when(this.mockElementFactory.createStatementFromText(defaultText, null)).thenReturn(
				mockIfStatementDelegate.self);

		IfStatementDelegate expectedResult = mock(IfStatementDelegate.class);
		when(this.mockNodeFactory.getNode(mockIfStatementDelegate.self)).thenReturn(expectedResult);

		IfStatementDelegate actualResult
				= this.builderUnderTest.buildIfStatement(mockInputCondition.self,
				mockInputThenBranch.self,
				mockInputElseBranch.self);

		assertEquals(expectedResult, actualResult, BAD_RETURN);
		mockIfStatementDelegate.verifyComponentsAreReplacedWith(mockInputCondition,
				mockInputThenBranch,
				mockInputElseBranch);
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