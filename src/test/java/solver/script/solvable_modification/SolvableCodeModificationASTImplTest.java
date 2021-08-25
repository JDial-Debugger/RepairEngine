package solver.script.solvable_modification;

import ast.interfaces.*;
import ast.psi.NodeFactory;
import ast.psi.NodeFactoryImpl;
import ast.psi.PsiElementExtractorImpl;
import ast.psi.PsiNodeBuilder;
import ast.psi.factory.ArrayStringBuilderImpl;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiType;
import com.intellij.psi.impl.PsiElementFactoryImpl;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import solver.script.constraint.SemanticConstraintsAST;
import solver.script.constraint.SemanticConstraintsASTImpl;
import solver.script.state_record.StateRecord;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SolvableCodeModificationASTImplTest extends LightJavaCodeInsightFixtureTestCase {

	private SolvableCodeModificationASTImpl astToTest;
	private NodeBuilder nodeBuilder;
	private NodeFactory nodeFactory;
	private String holePlaceholderName = "jdialHolePlaceholder";

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

		WriteCommandAction.writeCommandAction(getProject()).run(() -> {
			Expression holePlaceholder = this.nodeBuilder.buildExpressionFromText(this.holePlaceholderName);
			this.astToTest = new SolvableCodeModificationASTImpl(this.nodeBuilder, holePlaceholder);
		});
	}

	@Test
	public void testGetStateRecordChangedConstraint() {
		WriteCommandAction.writeCommandAction(getProject()).run(() -> {
			Expression exprToModify = this.nodeBuilder.buildExpressionFromText("5");
			SolvableModificationId inputModificationId = new SolvableModificationId("func", "var");
			SolvableCodeModification inputModification = new SolvableCodeModification(exprToModify, inputModificationId);
			List<Node> result = this.astToTest.getInitializationCode(inputModification);

			String expectedChangeVarDecl = "int var = jdialHolePlaceholder;";
			assertEquals(result.get(0).toString(), expectedChangeVarDecl);

			String expectedChangeMethod = "int func() {\n"
					+ "if(jdialHolePlaceholder){\n"
					+ "return 0;}return var;}";
			assertEquals(expectedChangeMethod, result.get(1).toString());
		});
	}

	@Test
	public void testGetSolvableCode() {

		WriteCommandAction.writeCommandAction(getProject()).run(() -> {

			Expression exprToModify = this.nodeBuilder.buildExpressionFromText("5");

			SolvableModificationId inputModificationId = new SolvableModificationId("func", "var");
			SolvableCodeModification inputModification = new SolvableCodeModification(exprToModify, inputModificationId);

			Expression actualSolvableCode = this.astToTest.getSolvableCode(inputModification);

			String expectedSolvableCode = "5+func()";
			assertEquals(expectedSolvableCode, actualSolvableCode.toString());
		});
	}
}
