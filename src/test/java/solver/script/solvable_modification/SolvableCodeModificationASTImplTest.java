package solver.script.solvable_modification;

import ast.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SolvableCodeModificationASTImplTest {

	private SolvableCodeModificationASTImpl astToTest;
	private NodeFactory mockNodeFactory;
	private ExpressionDelegate mockOriginalCode;
	private ExpressionDelegate mockHolePlaceholder;
	private TypeDelegate mockModificationType;
	private final String expectedNoChangeRawValue = "0";

	@BeforeEach
	void SetUp() {
		this.mockNodeFactory = mock(NodeFactory.class);
		this.mockHolePlaceholder = mock(ExpressionDelegate.class);
		this.mockModificationType = mock(TypeDelegate.class);
		this.mockOriginalCode = mock(ExpressionDelegate.class);
		when(this.mockOriginalCode.getType()).thenReturn(mockModificationType);
		this.astToTest = new SolvableCodeModificationASTImpl(this.mockNodeFactory,
				mockHolePlaceholder);
	}

	@Test
	void getInitializationStatement() {
		String sampleFuncName = "foo";
		String sampleVarName = "bar";

		DeclarationStatementDelegate expectedChangeVarDecl
				= mock(DeclarationStatementDelegate.class);
		when(this.mockNodeFactory.getDeclarationStatement(sampleVarName,
				mockModificationType,
				mockHolePlaceholder)).thenReturn(expectedChangeVarDecl);

		ExpressionDelegate noChangeValue = mock(ExpressionDelegate.class);
		when(this.mockNodeFactory.getExpressionFromText(this.expectedNoChangeRawValue)).thenReturn(
				noChangeValue);

		StatementDelegate expectedNoChangeReturn = mock(StatementDelegate.class);
		when(this.mockNodeFactory.getReturnStatement(noChangeValue)).thenReturn(
				expectedNoChangeReturn);

		CodeBlockDelegate expectedNoChangeIfBody = mock(CodeBlockDelegate.class);

		IfStatementDelegate expectedNoChangeIfStatement = mock(IfStatementDelegate.class);
		when(this.mockNodeFactory.getIfStatement(this.mockHolePlaceholder,
				expectedNoChangeIfBody)).thenReturn(expectedNoChangeIfStatement);

		ExpressionDelegate expectedChangeVar = mock(ExpressionDelegate.class);
		when(this.mockNodeFactory.getExpressionFromText(sampleVarName)).thenReturn(expectedChangeVar);

		StatementDelegate expectedChangeReturn = mock(StatementDelegate.class);
		when(this.mockNodeFactory.getReturnStatement(expectedChangeVar)).thenReturn(
				expectedChangeReturn);

		CodeBlockDelegate expectedMethodBody = mock(CodeBlockDelegate.class);
		when(this.mockNodeFactory.getEmptyCodeBlock()).thenReturn(expectedNoChangeIfBody,
				expectedMethodBody);

		MethodDelegate expectedMethod = mock(MethodDelegate.class);
		when(this.mockNodeFactory.getMethod(mockModificationType,
				sampleFuncName,
				null,
				expectedMethodBody)).thenReturn(expectedMethod);

		SolvableModificationIds id = new SolvableModificationIds(sampleFuncName, sampleVarName);
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
	}
}