package solver.script.solvable_modification;

import ast.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

class SolvableModificationVisitorTest {

	private SolvableCodeModificationAST mockAST;
	private Set<SolvableCodeModification> mockModifications;
	private SolvableModificationIDGenerator mockIdGenerator;
	private SolvableModificationVisitor visitorToTest;

	@BeforeEach
	void setUp() {
		this.mockAST = mock(SolvableCodeModificationAST.class);
		this.mockModifications = mock(Set.class);
		this.mockIdGenerator = mock(SolvableModificationIDGenerator.class);
		this.visitorToTest = new SolvableModificationVisitor(
				this.mockAST,
				this.mockModifications,
				this.mockIdGenerator);
	}

	@Test
	void visitBinaryExpression() {
		SolvableModificationId sampleId = this.mockSampleIds();
		BinaryExpressionDelegate mockExpressionToSolve = this.createMockBinaryExpressionToSolve();
		SolvableCodeModification expectedModification = new SolvableCodeModification(mockExpressionToSolve, sampleId);

		ExpressionDelegate expectedSolvableCode = mock(ExpressionDelegate.class);
		when(this.mockAST.getSolvableCode(eq(expectedModification)))
				.thenReturn(expectedSolvableCode);

		this.visitorToTest.visitBinaryExpression(mockExpressionToSolve);

		verify(mockExpressionToSolve).replace(expectedSolvableCode);
		verify(this.mockModifications).add(eq(expectedModification));
	}

	private SolvableModificationId mockSampleIds() {
		String funcName = "func";
		SolvableModificationId sampleId = new SolvableModificationId("func", "");
		when(this.mockIdGenerator.getSolvableModificationId())
				.thenReturn(sampleId);
		return sampleId;
	}

	@Test
	void visitUnaryExpression() {
		SolvableModificationId sampleId = this.mockSampleIds();
		UnaryExpressionDelegate mockExpressionToSolve = this.createMockUnaryExpressionToSolve();
		SolvableCodeModification expectedModification = new SolvableCodeModification(mockExpressionToSolve, sampleId);

		ExpressionDelegate expectedSolvableCode = mock(ExpressionDelegate.class);
		when(this.mockAST.getSolvableCode(eq(expectedModification)))
				.thenReturn(expectedSolvableCode);

		this.visitorToTest.visitUnaryExpression(mockExpressionToSolve);

		verify(mockExpressionToSolve).replace(expectedSolvableCode);
		verify(this.mockModifications).add(eq(expectedModification));
	}

	@Test
	void visitAssignExpression() {
		SolvableModificationId sampleId = this.mockSampleIds();
		AssignExpressionDelegate mockExpressionToSolve = this.createMockAssignExpressionToSolve();
		SolvableCodeModification expectedModification = new SolvableCodeModification(mockExpressionToSolve, sampleId);

		ExpressionDelegate expectedSolvableCode = mock(ExpressionDelegate.class);
		when(this.mockAST.getSolvableCode(eq(expectedModification)))
				.thenReturn(expectedSolvableCode);

		this.visitorToTest.visitAssignExpression(mockExpressionToSolve);

		verify(mockExpressionToSolve).replace(expectedSolvableCode);
		verify(this.mockModifications).add(eq(expectedModification));
	}

	@Test
	void visitLiteralExpression() {
		SolvableModificationId sampleId = this.mockSampleIds();
		LiteralExpressionDelegate mockExpressionToSolve = this.createMockLiteralExpressionToSolve();
		SolvableCodeModification expectedModification = new SolvableCodeModification(mockExpressionToSolve, sampleId);

		ExpressionDelegate expectedSolvableCode = mock(ExpressionDelegate.class);
		when(this.mockAST.getSolvableCode(eq(expectedModification)))
				.thenReturn(expectedSolvableCode);

		this.visitorToTest.visitLiteralExpression(mockExpressionToSolve);

		verify(mockExpressionToSolve).replace(expectedSolvableCode);
		verify(this.mockModifications).add(eq(expectedModification));
	}

	@Test
	void visitCallExpression() {
		SolvableModificationId sampleId = this.mockSampleIds();
		CallExpressionDelegate mockExpressionToSolve = this.createMockCallExpressionToSolve();
		SolvableCodeModification expectedModification = new SolvableCodeModification(mockExpressionToSolve, sampleId);

		ExpressionDelegate expectedSolvableCode = mock(ExpressionDelegate.class);
		when(this.mockAST.getSolvableCode(eq(expectedModification)))
				.thenReturn(expectedSolvableCode);

		this.visitorToTest.visitCallExpression(mockExpressionToSolve);

		verify(mockExpressionToSolve).replace(expectedSolvableCode);
		verify(this.mockModifications).add(eq(expectedModification));
	}

	@Test
	void visitLambdaExpression() {
		SolvableModificationId sampleId = this.mockSampleIds();
		LambdaExpressionDelegate mockExpressionToSolve = this.createMockLambdaExpressionToSolve();
		SolvableCodeModification expectedModification = new SolvableCodeModification(mockExpressionToSolve, sampleId);

		ExpressionDelegate expectedSolvableCode = mock(ExpressionDelegate.class);
		when(this.mockAST.getSolvableCode(eq(expectedModification)))
				.thenReturn(expectedSolvableCode);

		this.visitorToTest.visitLambdaExpression(mockExpressionToSolve);

		verify(mockExpressionToSolve).replace(expectedSolvableCode);
		verify(this.mockModifications).add(eq(expectedModification));
	}

	private BinaryExpressionDelegate createMockBinaryExpressionToSolve() {
		BinaryExpressionDelegate sampleInput = mock(BinaryExpressionDelegate.class);
		createMockExpressionUtil(sampleInput);
		return sampleInput;
	}

	/**
	 * Create TypeDelegate and mock the type for each createMockXXXExpressionToSolve function
	 *
	 * @param sampleInput sample input provided (with specific expression type delegate)
	 */
	private void createMockExpressionUtil(ExpressionDelegate sampleInput) {
		TypeDelegate mockTypeDelegate = mock(TypeDelegate.class);
		when(mockTypeDelegate.asEnum()).thenReturn(Type.INT);
		when(sampleInput.getType()).thenReturn(mockTypeDelegate);
	}

	private UnaryExpressionDelegate createMockUnaryExpressionToSolve() {
		UnaryExpressionDelegate sampleInput = mock(UnaryExpressionDelegate.class);
		createMockExpressionUtil(sampleInput);
		return sampleInput;
	}

	private AssignExpressionDelegate createMockAssignExpressionToSolve() {
		AssignExpressionDelegate sampleInput = mock(AssignExpressionDelegate.class);
		createMockExpressionUtil(sampleInput);
		return sampleInput;
	}

	private LiteralExpressionDelegate createMockLiteralExpressionToSolve() {
		LiteralExpressionDelegate sampleInput = mock(LiteralExpressionDelegate.class);
		createMockExpressionUtil(sampleInput);
		return sampleInput;
	}

	private CallExpressionDelegate createMockCallExpressionToSolve() {
		CallExpressionDelegate sampleInput = mock(CallExpressionDelegate.class);
		createMockExpressionUtil(sampleInput);
		return sampleInput;
	}

	private LambdaExpressionDelegate createMockLambdaExpressionToSolve() {
		LambdaExpressionDelegate sampleInput = mock(LambdaExpressionDelegate.class);
		createMockExpressionUtil(sampleInput);
		return sampleInput;
	}
}