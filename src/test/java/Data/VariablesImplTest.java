package Data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class VariablesImplTest {

    private Map<String, Integer> mockVarNameToIntValue;
    private Map<String, Double> mockVarNameToDoubleValue;
    private Map<String, Long> mockVarNameToLongValue;
    private Map<String, Boolean> mockVarNameToBooleanValue;
    private Map<String, String> mockVarNameToStringValue;
    private Map<String, Character> mockVarNameToCharValue;
    private Map<String, Object> mockVarNameToObjectValue;
    private VariablesImpl variablesImplToTest;

    @BeforeEach
    public void setUp() throws Exception {
        mockVarNameToIntValue = mock(Map.class);
        mockVarNameToDoubleValue = mock(Map.class);
        mockVarNameToLongValue = mock(Map.class);
        mockVarNameToBooleanValue = mock(Map.class);
        mockVarNameToStringValue = mock(Map.class);
        mockVarNameToCharValue = mock(Map.class);
        mockVarNameToObjectValue = mock(Map.class);
        variablesImplToTest = new VariablesImpl(
                mockVarNameToIntValue,
                mockVarNameToDoubleValue,
                mockVarNameToLongValue,
                mockVarNameToBooleanValue,
                mockVarNameToStringValue,
                mockVarNameToCharValue,
                mockVarNameToObjectValue
        );
    }

    @Test
    public void getIntegerVariableValue() {
        String sampleVariableName = "var1";
        int sampleValue = 4;
        when(mockVarNameToIntValue.containsKey(sampleVariableName))
                .thenReturn(true);
        when(mockVarNameToIntValue.get(sampleVariableName))
                .thenReturn(sampleValue);
        int result = variablesImplToTest.getIntegerValue(sampleVariableName);
        assertEquals(sampleValue, result);
    }

    @Test
    public void getIntegerVariableValueWhenNoValueExists() {
        String sampleVariableName = "var1";
        when(mockVarNameToIntValue.containsKey(sampleVariableName))
                .thenReturn(false);
        assertThrows(NoSuchVariableException.class, () -> {
            variablesImplToTest.getIntegerValue(sampleVariableName);
        });
    }

}
