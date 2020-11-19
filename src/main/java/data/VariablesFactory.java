package data;

import java.util.Map;
import java.util.Optional;

public interface VariablesFactory {
    public Variables getVariables(
            Optional<Map<String, Integer>> varNameToIntValue,
            Optional<Map<String, Double>> varNameToDoubleValue,
            Optional<Map<String, Long>> varNameToLongValue,
            Optional<Map<String, Boolean>> varNameToBooleanValue,
            Optional<Map<String, String>> varNameToStringValue,
            Optional<Map<String, Character>> varNameToCharValue,
            Optional<Map<String, Object>> varNameToObjectValue
    );
}
