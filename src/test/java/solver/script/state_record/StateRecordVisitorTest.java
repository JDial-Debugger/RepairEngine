package solver.script.state_record;

import ast.interfaces.*;
import ast.psi.StatementImpl;
import factory.java.util.ListFactory;
import factory.java.util.StackFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StateRecordVisitorTest {

	private StateRecordVisitor visitorUnderTest;
	private StateRecordAST mockAst;
	private ListFactory mockListFactory;
	private StackFactory mockStackFactory;

	private Method mockInputMethod;
	private CodeBlock mockInputCodeBlock;
	private Statement[] mockInputStatements;
	private Stack<List<StateRecord>> mockSampleScopedVariables;
	private List<StateRecord> mockSampleBaseScope;

	private int paramCount;
	private List<StateRecord> expectedParamStateRecords;

	private static final String INPUT_METHOD_NAME = "method1";
	private static final StateRecord EXPECTED_DECLARED_STATE_RECORD1 = new StateRecord(
			"var1",
			INPUT_METHOD_NAME,
			mock(Type.class));
	private static final StateRecord EXPECTED_DECLARED_STATE_RECORD2 = new StateRecord(
			"var2",
			INPUT_METHOD_NAME,
			mock(Type.class));
	private static final StateRecord[] EXPECTED_AVAILABLE_STATE_RECORDS = new StateRecord[] {
			new StateRecord("param1", INPUT_METHOD_NAME, mock(Type.class)),
			new StateRecord("param2", INPUT_METHOD_NAME, mock(Type.class)),
			new StateRecord("local_var1", INPUT_METHOD_NAME, mock(Type.class)),
			EXPECTED_DECLARED_STATE_RECORD1,
			EXPECTED_DECLARED_STATE_RECORD2
	};

	private static final Statement[][] SAMPLE_RECORD_STATEMENTS = new Statement[][] {
			{
					mock(Statement.class),
					mock(Statement.class),
					mock(Statement.class),
					mock(Statement.class),
					mock(Statement.class)
			},
			{
					mock(Statement.class),
					mock(Statement.class),
					mock(Statement.class),
					mock(Statement.class),
					mock(Statement.class)
			}
	};

	@BeforeEach
	void setUp() {
		this.paramCount = 0;
		this.mockAst = mock(StateRecordAST.class);
		this.mockListFactory = mock(ListFactory.class);
		this.mockStackFactory = mock(StackFactory.class);
		this.visitorUnderTest = new StateRecordVisitor(
				this.mockAst,
				this.mockListFactory,
				this.mockStackFactory
		);
		this.expectedParamStateRecords = new ArrayList<>();
	}

	@Test
	void visitMethod() {
		this.setInputs();
		this.mockSampleCollections();
		this.verifyVisitorAddsParamsToBaseScope();
	}

	private void setInputs() {
		this.mockInputMethod = mock(Method.class);

		when(this.mockInputMethod.getName()).thenReturn(INPUT_METHOD_NAME);

		this.mockInputParamList();
		this.mockInputCodeBlock();
	}

	private void mockInputParamList() {
		ParameterList mockInputParamList = mock(ParameterList.class);
		when(mockInputMethod.getParameterList()).thenReturn(mockInputParamList);

		Parameter[] mockInputParams = {
				this.getMockParam(), this.getMockParam(), this.getMockParam(),
		};
		when(mockInputParamList.getParameters()).thenReturn(mockInputParams);
	}

	private void mockInputCodeBlock() {
		this.mockInputCodeBlock = mock(CodeBlock.class);
		when(this.mockInputMethod.getBody()).thenReturn(this.mockInputCodeBlock);
	}

	private void mockSampleCollections() {
		this.mockSampleScopedVariables = mock(Stack.class);
		when(this.mockStackFactory.<List<StateRecord>>createStack()).thenReturn(this.mockSampleScopedVariables);

		this.mockSampleBaseScope = mock(Stack.class);
		when(this.mockListFactory.<StateRecord>createEmptyArrayList()).thenReturn(this.mockSampleBaseScope);

		when(this.mockSampleScopedVariables.peek()).thenReturn(this.mockSampleBaseScope);
	}

	private Parameter getMockParam() {
		Parameter mockParam = mock(Parameter.class);

		String paramName = "param" + this.paramCount++;
		when(mockParam.getName()).thenReturn(paramName);

		Type mockType = mock(Type.class);
		when(mockParam.getType()).thenReturn(mockType);

		this.expectedParamStateRecords.add(new StateRecord(
				paramName,
				INPUT_METHOD_NAME,
				mockType
		));

		return mockParam;
	}

	private void verifyVisitorAddsParamsToBaseScope() {

		this.visitorUnderTest.visitMethod(mockInputMethod);

		verify(this.mockInputMethod).acceptChildren(this.visitorUnderTest);
		verify(this.mockSampleScopedVariables).push(this.mockSampleBaseScope);
		for (StateRecord expectedParamStateRecord : this.expectedParamStateRecords) {
			verify(this.mockSampleBaseScope).add(eq(expectedParamStateRecord));
		}
	}

	@Test
	void visitCodeBlock() throws NoSuchFieldException, IllegalAccessException {
		this.setInputsForVisitCodeBlock();

		this.setupScopedVariables();
		this.setMethodName();

		this.setUpMockAst();

		this.visitorUnderTest.visitCodeBlock(this.mockInputCodeBlock);

		this.verifyVisitorAddsRecordStatementsAfterCorrectStatements();
	}

	private void setInputsForVisitCodeBlock() {
		this.mockInputCodeBlock = mock(CodeBlock.class);
		this.mockInputStatements = new Statement[] {
				mock(DeclarationStatement.class),
				mock(ExpressionStatement.class),
				mock(ReturnStatement.class)
		};
		this.mockDeclarationStatement();
		when(this.mockInputCodeBlock.getStatements()).thenReturn(this.mockInputStatements);

	}

	private void mockDeclarationStatement() {
		LocalVariable mockVar1 = mock(LocalVariable.class);
		when(mockVar1.getName()).thenReturn(EXPECTED_DECLARED_STATE_RECORD1.variableName);
		when(mockVar1.getType()).thenReturn(EXPECTED_DECLARED_STATE_RECORD1.variableType);

		LocalVariable mockVar2 = mock(LocalVariable.class);
		when(mockVar2.getName()).thenReturn(EXPECTED_DECLARED_STATE_RECORD2.variableName);
		when(mockVar2.getType()).thenReturn(EXPECTED_DECLARED_STATE_RECORD2.variableType);

		when(((DeclarationStatement) this.mockInputStatements[0])
				.getDeclaredLocalVariables()).thenReturn(Arrays.asList(mockVar1, mockVar2));
	}

	private void setupScopedVariables() throws NoSuchFieldException, IllegalAccessException {
		Stack<List<StateRecord>> sampleScopedVariables = new Stack<>();
		sampleScopedVariables.push(Arrays.asList(
				EXPECTED_AVAILABLE_STATE_RECORDS[0],
				EXPECTED_AVAILABLE_STATE_RECORDS[1]));
		sampleScopedVariables.push(Arrays.asList(
				EXPECTED_AVAILABLE_STATE_RECORDS[2]));

		List<StateRecord> curScopeSpy = spy(ArrayList.class);
		when(this.mockListFactory.<StateRecord>createEmptyArrayList()).thenReturn(
				curScopeSpy,
				new ArrayList<>(),
				new ArrayList<>());

		Field scopedVariablesField = this.visitorUnderTest
				.getClass()
				.getDeclaredField("scopedVariables");
		scopedVariablesField.setAccessible(true);
		scopedVariablesField.set(this.visitorUnderTest, sampleScopedVariables);
	}

	private void setMethodName() throws NoSuchFieldException, IllegalAccessException {

		Field curMethodNameField = this.visitorUnderTest
				.getClass()
				.getDeclaredField("curMethodName");
		curMethodNameField.setAccessible(true);
		curMethodNameField.set(this.visitorUnderTest, INPUT_METHOD_NAME);
	}

	private void setUpMockAst() {
		for (int j = 0; j < EXPECTED_AVAILABLE_STATE_RECORDS.length; ++j) {
			when(this.mockAst.getRecordStatement(eq(EXPECTED_AVAILABLE_STATE_RECORDS[j])))
					.thenReturn(SAMPLE_RECORD_STATEMENTS[0][j], SAMPLE_RECORD_STATEMENTS[1][j]);
		}
	}

	private void verifyVisitorAddsRecordStatementsAfterCorrectStatements() {
		for (int i = 0; i < SAMPLE_RECORD_STATEMENTS.length; ++i) {
			for (int j = 0; j < SAMPLE_RECORD_STATEMENTS[0].length; ++j) {
				verify(this.mockInputCodeBlock).addAfter(
						SAMPLE_RECORD_STATEMENTS[i][j],
						this.mockInputStatements[i]);
			}
		}
	}
}