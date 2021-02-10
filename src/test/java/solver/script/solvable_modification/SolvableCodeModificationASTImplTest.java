package solver.script.solvable_modification;

import ast.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SolvableCodeModificationASTImplTest {

	private SolvableCodeModificationASTImpl astToTest;
	private NodeBuilder mockNodeBuilder;
	private ExpressionDelegate mockOriginalCode;
	private ExpressionDelegate mockHolePlaceholder;
	private TypeDelegate mockModificationType;
	private final String expectedNoChangeRawValue = "0";

	@BeforeEach
	void SetUp() {
		this.mockNodeBuilder = mock(NodeBuilder.class);
		this.mockHolePlaceholder = mock(ExpressionDelegate.class);
		this.mockModificationType = mock(TypeDelegate.class);
		this.mockOriginalCode = mock(ExpressionDelegate.class);
		when(this.mockOriginalCode.getType()).thenReturn(mockModificationType);
		this.astToTest = new SolvableCodeModificationASTImpl(this.mockNodeBuilder,
				mockHolePlaceholder);
	}

	@Test
	void getInitializationStatement() {
		String sampleMethodName = "foo";
		String sampleVarName = "bar";

		DeclarationStatementDelegate expectedChangeVarDecl
				= mock(DeclarationStatementDelegate.class);
		when(this.mockNodeBuilder.buildDeclarationStatement(sampleVarName,
				mockModificationType,
				mockHolePlaceholder)).thenReturn(expectedChangeVarDecl);

		ExpressionDelegate noChangeValue = mock(ExpressionDelegate.class);
		when(this.mockNodeBuilder.buildExpressionFromText(this.expectedNoChangeRawValue)).thenReturn(
				noChangeValue);

		StatementDelegate expectedNoChangeReturn = mock(StatementDelegate.class);
		when(this.mockNodeBuilder.buildReturnStatement(noChangeValue)).thenReturn(
				expectedNoChangeReturn);

		CodeBlockDelegate expectedNoChangeIfBody = mock(CodeBlockDelegate.class);

		IfStatementDelegate expectedNoChangeIfStatement = mock(IfStatementDelegate.class);
		when(this.mockNodeBuilder.buildIfStatement(this.mockHolePlaceholder,
				expectedNoChangeIfBody)).thenReturn(expectedNoChangeIfStatement);

		ExpressionDelegate expectedChangeVar = mock(ExpressionDelegate.class);
		when(this.mockNodeBuilder.buildExpressionFromText(sampleVarName)).thenReturn(expectedChangeVar);

		StatementDelegate expectedChangeReturn = mock(StatementDelegate.class);
		when(this.mockNodeBuilder.buildReturnStatement(expectedChangeVar)).thenReturn(
				expectedChangeReturn);

		CodeBlockDelegate expectedMethodBody = mock(CodeBlockDelegate.class);
		when(this.mockNodeBuilder.buildEmptyCodeBlock()).thenReturn(expectedNoChangeIfBody,
				expectedMethodBody);

		MethodDelegate expectedMethod = mock(MethodDelegate.class);
		when(this.mockNodeBuilder.buildMethod(mockModificationType,
				sampleMethodName,
				null,
				expectedMethodBody)).thenReturn(expectedMethod);

		SolvableModificationId id = new SolvableModificationId(sampleMethodName, sampleVarName);
		SolvableCodeModification sampleInput = new SolvableCodeModification(this.mockOriginalCode,
				id);
		List<NodeDelegate> result = this.astToTest.getInitializationCode(sampleInput);

		verify(expectedNoChangeIfBody).addStatement(expectedNoChangeReturn);
		verify(expectedMethodBody).addStatements(expectedNoChangeIfStatement, expectedChangeReturn);

		assertEquals(expectedChangeVarDecl,
				result.get(0),
				"Change variable declaration did not match with factory result");
		assertEquals(expectedMethod, result.get(1), "Method did not match with factory result");
	}

	@Test
	void getSolvableCode() {
		String sampleMethodName = "foo";
		SolvableModificationId ids = new SolvableModificationId(sampleMethodName, null);
		ExpressionDelegate sampleOriginalCode = mock(ExpressionDelegate.class);

		ExpressionDelegate expectedMethodCall = mock(ExpressionDelegate.class);
		when(this.mockNodeBuilder.buildMethodCall(sampleMethodName)).thenReturn(expectedMethodCall);

		BinaryExpressionDelegate mockSolvableCode = mock(BinaryExpressionDelegate.class);
		when(this.mockNodeBuilder.buildBinaryExpression(
				sampleOriginalCode,
				BinaryOperator.ADD,
				expectedMethodCall)).thenReturn(mockSolvableCode);

		SolvableCodeModification sampleInput = new SolvableCodeModification(
				sampleOriginalCode,
				ids);

		ExpressionDelegate resultSolvableCode = this.astToTest.getSolvableCode(sampleInput);
		assertEquals(
				mockSolvableCode,
				resultSolvableCode,
				"Did not use psi factory to create binary expression of solvable code");
	}
}