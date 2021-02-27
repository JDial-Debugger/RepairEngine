package solver.script.constraint;

import ast.interfaces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.script.solvable_modification.SolvableCodeModificationAST;
import solver.script.solvable_modification.SolvableModificationId;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

public class SyntacticConstraintsASTTests {
	private SyntacticConstraintsASTImpl astUnderTest;

	private NodeBuilder mockNodeBuilder;
	private SolvableCodeModificationAST mockSolvableCodeModificationAST;

	private Expression sampleReferenceExpression;

	private final static String EXPECTED_ID = "__JDIAL__syntactic_distance";

	@BeforeEach
	public void setUp() {
		this.mockNodeBuilder = mock(NodeBuilder.class);
		this.sampleReferenceExpression = mock(Expression.class);
		this.mockSolvableCodeModificationAST = mock(SolvableCodeModificationAST.class);
		this.astUnderTest = new SyntacticConstraintsASTImpl(
				this.mockNodeBuilder,
				this.mockSolvableCodeModificationAST);
	}

	@Test
	public void testGetConstraintReferenceExpression() {
		when(this.mockNodeBuilder.buildExpressionFromText(eq(EXPECTED_ID)))
				.thenReturn(this.sampleReferenceExpression);
		Expression actualResult = this.astUnderTest.getConstraintReferenceExpression();
		assertEquals(this.sampleReferenceExpression, actualResult, "Did not return builder result");
	}

	@Test
	public void testGetInitializationStatementsOnNoIds() {
		DeclarationStatement expectedStatement = this.stubDeclarationStatementCreation();
		List<Statement> actualInitializationStatements
				= this.astUnderTest.getInitializationStatements(new ArrayList<>());
		assertThat(actualInitializationStatements, hasSize(1));
		assertEquals(expectedStatement, actualInitializationStatements.get(0));
	}

	private DeclarationStatement stubDeclarationStatementCreation() {
		Type mockType = mock(Type.class);
		when(this.mockNodeBuilder.buildType(PrimitiveType.INT))
				.thenReturn(mockType);
		LiteralExpression mockInitializer = mock(LiteralExpression.class);
		when(this.mockNodeBuilder.buildLiteralIntExpression(0))
				.thenReturn(mockInitializer);
		DeclarationStatement expectedDeclaration = mock(DeclarationStatement.class);
		when(this.mockNodeBuilder.buildDeclarationStatement(EXPECTED_ID, mockType, mockInitializer))
				.thenReturn(expectedDeclaration);
		return expectedDeclaration;
	}

	@Test
	public void testGetInitializationStatements() {
		when(this.mockNodeBuilder.buildExpressionFromText(eq(EXPECTED_ID)))
				.thenReturn(this.sampleReferenceExpression);

		List<Statement> expectedStatements = new ArrayList<>();
		expectedStatements.add(this.stubDeclarationStatementCreation());

		List<SolvableModificationId> inputIds = new ArrayList<>();
		List<Statement> expectedAddStatements = this.getAndStubExpectedStatements(inputIds);

		expectedStatements.addAll(expectedAddStatements);

		List<Statement> actualInitializationStatements
				= this.astUnderTest.getInitializationStatements(inputIds);

		assertEquals(expectedStatements, actualInitializationStatements);
	}

	private List<Statement> getAndStubExpectedStatements(List<SolvableModificationId> inputIds) {
		List<Statement> expectedStatements = new ArrayList<>();
		for (int i = 0; i < 3; ++i) {
			String changeVarName = "changeVar" + i;
			inputIds.add(new SolvableModificationId("method" + i, changeVarName));
			expectedStatements.add(stubExpectedStatementBuild(changeVarName));
		}
		return expectedStatements;
	}

	private Statement stubExpectedStatementBuild(String changeVarName) {
		Expression mockChangeVar = mock(Expression.class);
		when(this.mockNodeBuilder.buildExpressionFromText(changeVarName))
				.thenReturn(mockChangeVar);

		AssignExpression mockAddChange = mock(AssignExpression.class);
		when(this.mockNodeBuilder.buildAssignExpression(this.sampleReferenceExpression,
				AssignOperator.ADD, mockChangeVar))
				.thenReturn(mockAddChange);

		ExpressionStatement mockAddChangeStmt = mock(ExpressionStatement.class);
		when(this.mockNodeBuilder.buildExpressionStatement(mockAddChange))
				.thenReturn(mockAddChangeStmt);

		return mockAddChangeStmt;
	}
}
