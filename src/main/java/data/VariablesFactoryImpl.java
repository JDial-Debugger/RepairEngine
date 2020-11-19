package data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VariablesFactoryImpl implements VariablesFactory {

	@Override
	public Variables getVariables(
			Optional<Map<String, Integer>> varNameToIntValue,
			Optional<Map<String, Double>> varNameToDoubleValue,
			Optional<Map<String, Long>> varNameToLongValue,
			Optional<Map<String, Boolean>> varNameToBooleanValue,
			Optional<Map<String, String>> varNameToStringValue,
			Optional<Map<String, Character>> varNameToCharValue,
			Optional<Map<String, Object>> varNameToObjectValue) {
		/*return new Variables(
				varNameToIntValue.isPresent() ? varNameToIntValue.get() : getMap(Integer.class),
		)*/
		return null;
	}

	private <V> HashMap<String, V> getMap(Class<V> type) {
		return new HashMap<String, V>();
	}
}
