package data;

import factory.java.util.SetFactory;

import java.util.Map;
import java.util.Set;

//  TODO: Implement and test variables other than ints
public class VariablesImpl implements Variables {

	private final SetFactory setFactory;
	private final Map<String, Object> varNameToVarValue;

	public VariablesImpl(
			SetFactory setFactory, Map<String, Object> varNameToVarValue) {
		this.setFactory = setFactory;
		this.varNameToVarValue = varNameToVarValue;
	}

	@Override
	public Class<?> getTypeOf(String name) {
		return null;
	}

	@Override
	public <TValue> TValue getValue(String name, Class<TValue> type) {
		if (this.varNameToVarValue.containsKey(name)) {
			return attemptValueRetrieval(name, type);
		}
		throw new NoSuchVariableException(name, Integer.class);
	}

	private <TValue> TValue attemptValueRetrieval(String varName, Class<TValue> type) {
		Object valueRetrieved = this.varNameToVarValue.get(varName);
		try {
			return type.cast(valueRetrieved);
		} catch (ClassCastException ex) {
			throw new WrongVariableTypeException(valueRetrieved.getClass(), type);
		}
	}

	@Override
	public <TValue> Set<Variable<TValue>> getAllOfType(Class<TValue> type) {
		Set<Variable<TValue>> result = this.setFactory.<Variable<TValue>>createHashSet();
		for (Map.Entry<String, Object> entry : this.varNameToVarValue.entrySet()) {
			attemptAddVariable(entry.getKey(), entry.getValue(), result, type);
		}
		return result;
	}

	private <TValue> void attemptAddVariable(
			String varName,
			Object varValue,
			Set<Variable<TValue>> variables,
			Class<TValue> type) {
		if (type.isAssignableFrom(varValue.getClass())) {
			variables.add(new Variable<TValue>(varName, type.cast(varValue)));
		}
	}

	@Override
	public Variables addIntegers(Map<String, Integer> nameToIntValue) {
		this.varNameToVarValue.putAll(nameToIntValue);
		return this;
	}

	@Override
	public Variables addBooleans(Map<String, Boolean> nameToBoolValue) {
		this.varNameToVarValue.putAll(nameToBoolValue);
		return this;
	}

	@Override
	public Variables addDoubles(Map<String, Double> nameToDoubleValue) {
		this.varNameToVarValue.putAll(nameToDoubleValue);
		return this;
	}

	@Override
	public Variables addLongs(Map<String, Long> nameToLongValue) {
		this.varNameToVarValue.putAll(nameToLongValue);
		return this;
	}

	@Override
	public Variables addStrings(Map<String, String> nameToStringValue) {
		this.varNameToVarValue.putAll(nameToStringValue);
		return this;
	}

	@Override
	public Variables addCharacters(Map<String, Character> nameToCharValue) {
		this.varNameToVarValue.putAll(nameToCharValue);
		return this;
	}

	@Override
	public Variables addObjects(Map<String, Object> nameToObjectValue) {
		this.varNameToVarValue.putAll(nameToObjectValue);
		return this;
	}
}
