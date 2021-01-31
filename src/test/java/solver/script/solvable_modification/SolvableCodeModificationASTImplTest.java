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
	private Expression mockOriginalCode;
	private Expression mockHolePlaceholder;
	private Type mockModificationType;
	private final String expectedNoChangeRawValue = "0";

	@BeforeEach
	void SetUp() {
		this.mockNodeBuilder = mock(NodeBuilder.class);
		this.mockHolePlaceholder = mock(Expression.class);
		this.mockModificationType = mock(Type.class);
		this.mockOriginalCode = mock(Expression.class);
		when(this.mockOriginalCode.getType()).thenReturn(mockModificationType);
		this.astToTest = new SolvableCodeModificationASTImpl(this.mockNodeBuilder,
				mockHolePlaceholder);
	}

	@Test
	void getInitializationStatement() {
		String sampleMethodName = "foo";
		String sampleVarName = "bar";

		DeclarationStatement expectedChangeVarDecl
				= mock(DeclarationStatement.class);
		when(this.mockNodeBuilder.buildDeclarationStatement(sampleVarName,
				mockModificationType,
				mockHolePlaceholder)).thenReturn(expectedChangeVarDecl);

		Expression noChangeValue = mock(Expression.class);
		when(this.mockNodeBuilder.buildExpressionFromText(this.expectedNoChangeRawValue)).thenReturn(
				noChangeValue);

		Statement expectedNoChangeReturn = mock(Statement.class);
		when(this.mockNodeBuilder.buildReturnStatement(noChangeValue)).thenReturn(
				expectedNoChangeReturn);

		CodeBlock expectedNoChangeIfBody = mock(CodeBlock.class);

		IfStatement expectedNoChangeIfStatement = mock(IfStatement.class);
		when(this.mockNodeBuilder.buildIfStatement(this.mockHolePlaceholder,
				expectedNoChangeIfBody)).thenReturn(expectedNoChangeIfStatement);

		Expression expectedChangeVar = mock(Expression.class);
		when(this.mockNodeBuilder.buildExpressionFromText(sampleVarName)).thenReturn(expectedChangeVar);

		Statement expectedChangeReturn = mock(Statement.class);
		when(this.mockNodeBuilder.buildReturnStatement(expectedChangeVar)).thenReturn(
				expectedChangeReturn);

		CodeBlock expectedMethodBody = mock(CodeBlock.class);
		when(this.mockNodeBuilder.buildEmptyCodeBlock()).thenReturn(expectedNoChangeIfBody,
				expectedMethodBody);

		Method expectedMethod = mock(Method.class);
		when(this.mockNodeBuilder.buildMethod(mockModificationType,
				sampleMethodName,
				null,
				expectedMethodBody)).thenReturn(expectedMethod);

		SolvableModificationId id = new SolvableModificationId(sampleMethodName, sampleVarName);
		SolvableCodeModification sampleInput = new SolvableCodeModification(this.mockOriginalCode,
				id);
		List<Node> result = this.astToTest.getInitializationCode(sampleInput);

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
		Expression sampleOriginalCode = mock(Expression.class);

		Expression expectedMethodCall = mock(Expression.class);
		when(this.mockNodeBuilder.buildMethodCall(sampleMethodName)).thenReturn(expectedMethodCall);

		BinaryExpression mockSolvableCode = mock(BinaryExpression.class);
		when(this.mockNodeBuilder.buildBinaryExpression(
				sampleOriginalCode,
				BinaryOperator.ADD,
				expectedMethodCall)).thenReturn(mockSolvableCode);

		SolvableCodeModification sampleInput = new SolvableCodeModification(
				sampleOriginalCode,
				ids);

		Expression resultSolvableCode = this.astToTest.getSolvableCode(sampleInput);
		assertEquals(
				mockSolvableCode,
				resultSolvableCode,
				"Did not use psi factory to create binary expression of solvable code");
	}
}