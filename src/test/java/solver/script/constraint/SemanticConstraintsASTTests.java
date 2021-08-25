package solver.script.constraint;

import ast.interfaces.NodeBuilder;
import ast.interfaces.Statement;
import ast.interfaces.Type;
import ast.psi.*;
import ast.psi.factory.ArrayStringBuilderImpl;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiType;
import com.intellij.psi.impl.PsiElementFactoryImpl;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import solver.script.state_record.StateRecord;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;

public class SemanticConstraintsASTTests extends LightJavaCodeInsightFixtureTestCase {

	private Project project;
	private NodeBuilder nodeBuilder;
	private NodeFactory nodeFactory;
	private SemanticConstraintsAST astUnderTest;
	private final int inputExampleCount = 3;
	private final int inputExecutionLength = 10;
	private final String inputVarName = "var1";
	private final String inputFuncName = "func1";
	private final String expectedSemanticDistanceId = "__JDIAL__semantic_distance";
	private final String expectedChangedStateRecordId = "__JDIAL__func1_var1_state";
	private final String expectedOriginalStateRecordId = "__JDIAL__func1_var1_original_state";

	@BeforeEach
	public void setup() throws Exception {
		setUp();
		this.myFixture.configureByText("hello.java", "public class Main { public static void main(String[] args) {System.out.println(\"hey\");}}");
		this.nodeFactory = new NodeFactoryImpl(getProject());
		this.nodeBuilder = new PsiNodeBuilder(
				new PsiElementFactoryImpl(getProject()),
				new PsiElementExtractorImpl(),
				new ArrayStringBuilderImpl(),
				this.nodeFactory);
		this.astUnderTest = new SemanticConstraintsASTImpl(this.nodeBuilder, this.inputExampleCount, this.inputExecutionLength);
	}

	@Test
	public void testGetStateRecordChangedConstraint() {
		WriteCommandAction.writeCommandAction(getProject()).run(() -> {

			Type type = this.nodeFactory.getType(PsiType.INT);
			StateRecord input = new StateRecord("var1", "func1", type);

			Statement result = astUnderTest.getStateRecordChangedConstraint(input);

			String expectedResult = "for (int i = 0; i<" + this.inputExampleCount + "; ++(i))"
					+ "for (int j = 0; j<" + this.inputExecutionLength + "; ++(j))"
					+ expectedSemanticDistanceId + "+="
					+ expectedChangedStateRecordId + "[i][j]!=" + expectedOriginalStateRecordId + "[i][j];";
			assertEquals(expectedResult, result.toString());
		});
	}
}
