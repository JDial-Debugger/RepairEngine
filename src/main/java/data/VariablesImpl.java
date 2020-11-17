package data;

import java.util.Map;
import java.util.Set;

public class VariablesImpl implements Variables {

    private Map<String, Integer> varNameToIntValue;
    private Map<String, Double> varNameToDoubleValue;
    private Map<String, Long> varNameToLongValue;
    private Map<String, Boolean> varNameToBooleanValue;
    private Map<String, String> varNameToStringValue;
    private Map<String, Character> varNameToCharValue;
    private Map<String, Object> varNameToObjectValue;

    public VariablesImpl(
            Map<String, Integer> varNameToIntValue,
            Map<String, Double> varNameToDoubleValue,
            Map<String, Long> varNameToLongValue,
            Map<String, Boolean> varNameToBooleanValue,
            Map<String, String> varNameToStringValue,
            Map<String, Character> varNameToCharValue,
            Map<String, Object> varNameToObjectValue
    ) {
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
    public int getIntegerValue(String name) {
        if (varNameToIntValue.containsKey(name)) {
            return varNameToIntValue.get(name);
        }
        throw new NoSuchVariableException(name, Integer.class);
    }

    @Override
    public double getDoubleValue(String name) {
        return 0;
    }

    @Override
    public long getLongValue(String name) {
        return 0;
    }

    @Override
    public boolean getBooleanValue(String name) {
        return false;
    }

    @Override
    public String getStringValue(String name) {
        return null;
    }

    @Override
    public char getCharValue(String name) {
        return 0;
    }

    @Override
    public Object getCustomObjectValue(String name) {
        return null;
    }

    @Override
    public Set<Variable<Integer>> getAllIntegers() {
        return null;
    }

    @Override
    public Set<Variable<Double>> getAllDoubles() {
        return null;
    }

    @Override
    public Set<Variable<Long>> getAllLongs() {
        return null;
    }

    @Override
    public Set<Variable<Boolean>> getAllBooleans() {
        return null;
    }

    @Override
    public Set<Variable<String>> getAllStrings() {
        return null;
    }

    @Override
    public Set<Variable<Character>> getAllChars() {
        return null;
    }

    @Override
    public Set<Variable<Object>> getAllCustomObjects() {
        return null;
    }

    @Override
    public Set<Variable<?>> getAllVariables() {
        return null;
    }
}
