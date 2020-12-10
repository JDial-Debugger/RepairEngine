package solver.script.solvable_modification;

import ast.interfaces.BinaryExpressionDelegate;
import ast.interfaces.ExpressionDelegate;
import ast.interfaces.Type;
import ast.interfaces.TypeDelegate;
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
		BinaryExpressionDelegate mockExpressionToSolve = this.createMockExpressionToSolve();
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

	private BinaryExpressionDelegate createMockExpressionToSolve() {
		BinaryExpressionDelegate sampleInput = mock(BinaryExpressionDelegate.class);
		TypeDelegate mockTypeDelegate = mock(TypeDelegate.class);
		when(mockTypeDelegate.asEnum()).thenReturn(Type.INT);
		when(sampleInput.getType()).thenReturn(mockTypeDelegate);
		return sampleInput;
	}
}