package solver.script.state_record;

import ast.interfaces.ArrayDeclarationStatementDelegate;
import ast.interfaces.NodeFactory;
import ast.interfaces.StatementDelegate;
import ast.interfaces.TypeDelegate;
import ast.psi.PsiTypeDelegate;
import data.ProgramTrace;
import data.TraceUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Errors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

public class StateRecordASTImplTest {

	private StateRecordASTImpl astUnderTest;
	private NodeFactory mockNodeFactory;
	private String sampleStateIndexId;
	private static final String EXPECTED_ID = "__JDIAL__func1_var1_state_record";
	private static final String EXPECTED_VAR_NAME = "var1";
	private static final String EXPECTED_FUNC_NAME = "func1";

	@BeforeEach
	public void setup() {
		this.sampleStateIndexId = "__JDIAL__state_index";
		this.mockNodeFactory = mock(NodeFactory.class);
		this.astUnderTest = new StateRecordASTImpl(this.mockNodeFactory, this.sampleStateIndexId);
	}

	@Test
	public void getInitializationStatement() {

		Integer[] expectedDimensions = { 10, 4, 13, 20 };

		StateRecord sampleRecord = this.createSampleStateRecord();
		List<ProgramTrace> sampleTraces = this.createSampleTraces(expectedDimensions);

		ArrayDeclarationStatementDelegate mockFactoryResult
				= mock(ArrayDeclarationStatementDelegate.class);
		when(this.mockNodeFactory.getEmptyArrayDeclaration(eq(PsiTypeDelegate.INT),
				eq(EXPECTED_ID),
				aryEq(expectedDimensions))).thenReturn(mockFactoryResult);

		StatementDelegate result = this.astUnderTest.getInitializationStatement(sampleRecord,
				sampleTraces);

		assertEquals(mockFactoryResult, result, Errors.FAILED_FACTORY_RESULT);
	}

	private StateRecord createSampleStateRecord() {
		TypeDelegate sampleType = PsiTypeDelegate.INT;
		return new StateRecord(EXPECTED_VAR_NAME, EXPECTED_FUNC_NAME, sampleType);
	}

	private List<ProgramTrace> createSampleTraces(Integer[] expectedDimensions) {

		List<ProgramTrace> sampleTraces = new ArrayList<>();
		sampleTraces.add(new ProgramTrace() {{
			traceUnits = Arrays.asList(new TraceUnit[expectedDimensions[0]]);
		}});
		sampleTraces.add(new ProgramTrace() {{
			traceUnits = Arrays.asList(new TraceUnit[expectedDimensions[1]]);
		}});
		sampleTraces.add(new ProgramTrace() {{
			traceUnits = Arrays.asList(new TraceUnit[expectedDimensions[2]]);
		}});
		sampleTraces.add(new ProgramTrace() {{
			traceUnits = Arrays.asList(new TraceUnit[expectedDimensions[3]]);
		}});
		return sampleTraces;
	}

	@Test
	public void getInitializationStatementOnEmptyTrace() {
		StateRecord sampleRecord = this.createSampleStateRecord();
		List<ProgramTrace> emptyTraces = new ArrayList<>();

		ArrayDeclarationStatementDelegate mockFactoryResult
				= mock(ArrayDeclarationStatementDelegate.class);
		when(this.mockNodeFactory.getEmptyArrayDeclaration(eq(PsiTypeDelegate.INT),
				eq(EXPECTED_ID),
				aryEq(new Integer[0]))).thenReturn(mockFactoryResult);

		StatementDelegate result = this.astUnderTest.getInitializationStatement(sampleRecord,
				emptyTraces);

		assertEquals(mockFactoryResult, result, Errors.FAILED_FACTORY_RESULT);
	}

	@Test
	public void getRecordStatement() {
		StateRecord sampleRecord = this.createSampleStateRecord();

		StatementDelegate mockFactoryResult = mock(StatementDelegate.class);
		when(this.mockNodeFactory.getStatementFromText(EXPECTED_ID
				+ "["
				+ this.sampleStateIndexId
				+ "++] = "
				+ EXPECTED_VAR_NAME
				+ ";")).thenReturn(mockFactoryResult);

		StatementDelegate result = this.astUnderTest.getRecordStatement(sampleRecord);
		assertEquals(mockFactoryResult, result, Errors.FAILED_FACTORY_RESULT);
	}
}
