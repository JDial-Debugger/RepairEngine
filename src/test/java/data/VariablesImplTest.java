package data;

import collection.SetFactory;
import helper.MockEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VariablesImplTest {

	private SetFactory mockSetFactory;
	private Set mockSet;
	private Map<String, Integer> mockVarNameToIntValue;
	private Map<String, Double> mockVarNameToDoubleValue;
	private Map<String, Long> mockVarNameToLongValue;
	private Map<String, Boolean> mockVarNameToBooleanValue;
	private Map<String, String> mockVarNameToStringValue;
	private Map<String, Character> mockVarNameToCharValue;
	private Map<String, Object> mockVarNameToObjectValue;
	private VariablesImpl variablesImplToTest;

	private Map.Entry<String, Integer>[] sampleIntEntries = new Map.Entry[] {
			new MockEntry<String, Integer>("a", 1),
			new MockEntry<String, Integer>("b", 2),
			new MockEntry<String, Integer>("c", 3)
	};

	@Test
	public void test() {
		int a = 5;
		a += 3;
		int b = 3 + a;

	}

	private Variable<Integer>[] expectedInts = new Variable[] {
			new Variable("a", 1), new Variable("b", 2), new Variable("c", 3),
	};

	@BeforeEach
	public void setUp() throws Exception {
		mockVarNameToIntValue = mock(Map.class);
		when(mockVarNameToIntValue.entrySet()).thenReturn(Stream.of(sampleIntEntries)
				.collect(Collectors.toSet()));
		mockVarNameToDoubleValue = mock(Map.class);
		mockVarNameToLongValue = mock(Map.class);
		mockVarNameToBooleanValue = mock(Map.class);
		mockVarNameToStringValue = mock(Map.class);
		mockVarNameToCharValue = mock(Map.class);
		mockVarNameToObjectValue = mock(Map.class);
		mockSetFactory = mock(SetFactory.class);
		mockSet = mock(Set.class);
		when(mockSetFactory.getHashSet()).thenReturn(mockSet);

		variablesImplToTest = new VariablesImpl(
				mockSetFactory,
				mockVarNameToIntValue,
				mockVarNameToDoubleValue,
				mockVarNameToLongValue,
				mockVarNameToBooleanValue,
				mockVarNameToStringValue,
				mockVarNameToCharValue,
				mockVarNameToObjectValue);
	}

	@Test
	void getValueOnInteger() {
		String sampleVariableName = "var1";
		int sampleValue = 4;
		when(mockVarNameToIntValue.containsKey(sampleVariableName)).thenReturn(true);
		when(mockVarNameToIntValue.get(sampleVariableName)).thenReturn(sampleValue);
		int result = variablesImplToTest.getValue(sampleVariableName, Integer.class);
		assertEquals(sampleValue, result);
	}

	@Test
	void getValueWhenNoIntegerExists() {
		String sampleVariableName = "var1";
		when(mockVarNameToIntValue.containsKey(sampleVariableName)).thenReturn(false);
		assertThrows(NoSuchVariableException.class, () -> {
			variablesImplToTest.getValue(sampleVariableName, Integer.class);
		});
	}

	@Test
	void getTypeOf() {
	}

	@Test
	void getAllOfType() {
		Set<Variable<Integer>> result = variablesImplToTest.getAllOfType(Integer.class);
		assertEquals(mockSet, result);
		for (int i = 0; i < sampleIntEntries.length; ++i) {
			verify(mockSet).add(expectedInts[i]);
		}
	}
}
