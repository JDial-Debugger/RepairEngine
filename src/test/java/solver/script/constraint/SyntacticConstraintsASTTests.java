package solver.script.constraint;

import ast.interfaces.Expression;
import ast.interfaces.NodeBuilder;
import ast.interfaces.Statement;
import ast.interfaces.Type;
import ast.psi.NodeFactory;
import ast.psi.NodeFactoryImpl;
import ast.psi.PsiElementExtractorImpl;
import ast.psi.PsiNodeBuilder;
import ast.psi.factory.ArrayStringBuilderImpl;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiType;
import com.intellij.psi.impl.PsiElementFactoryImpl;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import solver.script.solvable_modification.SolvableCodeModification;
import solver.script.solvable_modification.SolvableModificationId;
import solver.script.state_record.StateRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SyntacticConstraintsASTTests extends LightJavaCodeInsightFixtureTestCase {

	private NodeBuilder nodeBuilder;
	private NodeFactory nodeFactory;
	private SyntacticConstraintsAST astUnderTest;

	@BeforeAll
	public void setup() throws Exception{
		setUp();
		this.myFixture.configureByText("hello.java", "public class Main { public static void main(String[] args) {System.out.println(\"hey\");}}");
	}

	@BeforeEach
	public void init() {
		this.nodeFactory = new NodeFactoryImpl(getProject());
		this.nodeBuilder = new PsiNodeBuilder(
				new PsiElementFactoryImpl(getProject()),
				new PsiElementExtractorImpl(),
				new ArrayStringBuilderImpl(),
				this.nodeFactory);
		this.astUnderTest = new SyntacticConstraintsASTImpl(this.nodeBuilder, null);
	}

	@Test
	public void testGetStateRecordChangedConstraint() {
		WriteCommandAction.writeCommandAction(getProject()).run(() -> {

			Type type = this.nodeFactory.getType(PsiType.INT);

			Expression actualExpr = astUnderTest.getConstraintReferenceExpression();

			String expectedResult = "__JDIAL__syntactic_distance";
			assertEquals(expectedResult, actualExpr.toString());
		});
	}

	@Test
	public void testGetInitializationStatements() {
		WriteCommandAction.writeCommandAction(getProject()).run(() -> {

			Type type = this.nodeFactory.getType(PsiType.INT);

			List<SolvableModificationId> inputIds = Arrays.asList(
					new SolvableModificationId("func1", "var1"),
					new SolvableModificationId("func2", "var2"),
					new SolvableModificationId("func3", "var3"));
			List<Statement> actualInitializationStatements
					= this.astUnderTest.getInitializationStatements(inputIds);

			List<String> expectedStatements = Arrays.asList(
					"int __JDIAL__syntactic_distance = 0;",
					"__JDIAL__syntactic_distance+=var1;",
					"__JDIAL__syntactic_distance+=var2;",
					"__JDIAL__syntactic_distance+=var3;"
			);

			List<String> fsd = actualInitializationStatements.stream().map(Object::toString).collect(
					Collectors.toList());

			assertThat(fsd, is(expectedStatements));
		});
	}

	@Test
	public void testGetInitializationStatementsOnNoIds() {
		WriteCommandAction.writeCommandAction(getProject()).run(() -> {

			Type type = this.nodeFactory.getType(PsiType.INT);

			List<Statement> actualInitializationStatements
					= this.astUnderTest.getInitializationStatements(new ArrayList<>());

			String expectedStatement = "int __JDIAL__syntactic_distance = 0;";
			assertEquals(expectedStatement, actualInitializationStatements.get(0).toString());
		});
	}
}
