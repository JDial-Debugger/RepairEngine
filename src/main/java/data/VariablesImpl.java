package data;

import collection.SetFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//  TODO: Implement and test variables other than ints
public class VariablesImpl implements Variables {

	private SetFactory setFactory;
	private Map<String, Integer> varNameToIntValue;
	private Map<String, Double> varNameToDoubleValue;
	private Map<String, Long> varNameToLongValue;
	private Map<String, Boolean> varNameToBooleanValue;
	private Map<String, String> varNameToStringValue;
	private Map<String, Character> varNameToCharValue;
	private Map<String, Object> varNameToObjectValue;

	public VariablesImpl(
			SetFactory setFactory,
			Map<String, Integer> varNameToIntValue,
			Map<String, Double> varNameToDoubleValue,
			Map<String, Long> varNameToLongValue,
			Map<String, Boolean> varNameToBooleanValue,
			Map<String, String> varNameToStringValue,
			Map<String, Character> varNameToCharValue,
			Map<String, Object> varNameToObjectValue) {
		this.setFactory = setFactory;
		this.varNameToIntValue = varNameToIntValue;
		this.varNameToDoubleValue = varNameToDoubleValue;
		this.varNameToLongValue = varNameToLongValue;
		this.varNameToBooleanValue = varNameToBooleanValue;
		this.varNameToStringValue = varNameToStringValue;
		this.varNameToCharValue = varNameToCharValue;
		this.varNameToObjectValue = varNameToObjectValue;
	}

	@Override
	public Class<?> getTypeOf(String name) {
		return null;
	}

	@Override
	public <TValue> TValue getValue(String name, Class<TValue> type) {
		Map<String, TValue> mapToUse;
		if (type == Integer.class) {
			mapToUse = (Map<String, TValue>) varNameToIntValue;
		} else {
			mapToUse = (Map<String, TValue>) varNameToObjectValue;
		}
		if (mapToUse.containsKey(name)) {
			return mapToUse.get(name);
		}
		throw new NoSuchVariableException(name, Integer.class);
	}

	@Override
	public <TValue> Set<Variable<TValue>> getAllOfType(Class<TValue> type) {
		Set<Variable<TValue>> result = this.setFactory.getHashSet();
		if (type == Integer.class) {
			addVariablesToResult(result, (Map<String, TValue>) varNameToIntValue);
		} else {
			addVariablesToResult(result, (Map<String, TValue>) varNameToObjectValue);
		}
		return result;
	}

	private <TValue> void addVariablesToResult(Set<Variable<TValue>> result, Map<String, TValue> map) {

		map.entrySet()
				.stream()
				.forEach(entry -> result.add(new Variable<TValue>(
						entry.getKey(),
						entry.getValue())));
	}
}
