package solver.script.state_record;

import ast.interfaces.*;
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
	private NodeBuilder mockNodeBuilder;
	private String sampleStateIndexId;
	private String sampleExampleIndexId;
	private static final String EXPECTED_ID = "__JDIAL__func1__var1__state_record";
	private static final String EXPECTED_VAR_NAME = "var1";
	private static final String EXPECTED_FUNC_NAME = "func1";

	@BeforeEach
	public void setup() {
		this.sampleStateIndexId = "__JDIAL__state_index";
		this.sampleExampleIndexId = "__JDIAL__example_index";
		this.mockNodeBuilder = mock(NodeBuilder.class);
		this.astUnderTest = new StateRecordASTImpl(this.mockNodeBuilder,
				this.sampleStateIndexId,
				this.sampleExampleIndexId);
	}

	@Test
	public void getInitializationStatement() {

		Integer[] expectedDimensions = { 10, 4, 13, 20 };

		StateRecord sampleRecord = this.createSampleStateRecord();
		List<ProgramTrace> sampleTraces = this.createSampleTraces(expectedDimensions);

		DeclarationStatement mockFactoryResult = mock(DeclarationStatement.class);
		Type mockType = this.getMockIntType();
		when(this.mockNodeBuilder.buildEmptyArrayDeclaration(mockType,
				eq(EXPECTED_ID),
				aryEq(expectedDimensions))).thenReturn(mockFactoryResult);

		Statement result = this.astUnderTest.getInitializationStatement(sampleRecord,
				sampleTraces);

		assertEquals(mockFactoryResult, result, Errors.FAILED_FACTORY_RESULT);
	}

	private Type getMockIntType() {
		Type sampleType = mock(Type.class);
		when(sampleType.asEnum()).thenReturn(PrimitiveType.INT);
		return sampleType;
	}

	private StateRecord createSampleStateRecord() {
		Type sampleType = this.getMockIntType();
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

		DeclarationStatement mockFactoryResult = mock(DeclarationStatement.class);
		Type mockType = this.getMockIntType();
		when(this.mockNodeBuilder.buildEmptyArrayDeclaration(mockType,
				eq(EXPECTED_ID),
				aryEq(new Integer[0]))).thenReturn(mockFactoryResult);

		Statement result = this.astUnderTest.getInitializationStatement(sampleRecord,
				emptyTraces);

		assertEquals(mockFactoryResult, result, Errors.FAILED_FACTORY_RESULT);
	}

	@Test
	public void getRecordStatement() {
		StateRecord sampleRecord = this.createSampleStateRecord();

		Statement mockFactoryResult = mock(Statement.class);
		when(this.mockNodeBuilder.buildStatementFromText(eq(EXPECTED_ID
				+ "["
				+ this.sampleExampleIndexId
				+ "]["
				+ this.sampleStateIndexId
				+ "] = "
				+ EXPECTED_VAR_NAME
				+ ";"))).thenReturn(mockFactoryResult);

		Statement result = this.astUnderTest.getRecordStatement(sampleRecord);
		assertEquals(mockFactoryResult, result, Errors.FAILED_FACTORY_RESULT);
	}
}
