package data;

import factory.java.util.SetFactory;
import helper.MockEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VariablesImplTest {

	private SetFactory mockSetFactory;
	private Set mockSet;
	private Map<String, Object> mockVarNameToValue;
	private VariablesImpl variablesImplToTest;

	private Map.Entry<String, Object>[] sampleIntEntries = new Map.Entry[] {
			new MockEntry<String, Object>("a", 1),
			new MockEntry<String, Object>("b", 2),
			new MockEntry<String, Object>("c", 3)
	};

	private Variable<Integer>[] expectedInts = new Variable[] {
			new Variable("a", 1), new Variable("b", 2), new Variable("c", 3),
	};

	@BeforeEach
	public void setUp() throws Exception {
		mockVarNameToValue = mock(Map.class);
		when(mockVarNameToValue.entrySet()).thenReturn(Stream.of(sampleIntEntries)
		                                                     .collect(Collectors.toSet()));
		mockSetFactory = mock(SetFactory.class);
		mockSet = mock(Set.class);
		when(mockSetFactory.getHashSet()).thenReturn(mockSet);

		variablesImplToTest = new VariablesImpl(mockSetFactory, mockVarNameToValue);
	}

	@Test
	void getValueOnInteger() {
		String sampleVariableName = "var1";
		int sampleValue = 4;
		when(mockVarNameToValue.containsKey(sampleVariableName)).thenReturn(true);
		when(mockVarNameToValue.get(sampleVariableName)).thenReturn(sampleValue);
		int result = variablesImplToTest.getValue(sampleVariableName, Integer.class);
		assertEquals(sampleValue, result);
	}

	@Test
	void getValueWhenNoIntegerExists() {
		String sampleVariableName = "var1";
		when(mockVarNameToValue.containsKey(sampleVariableName)).thenReturn(false);
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
