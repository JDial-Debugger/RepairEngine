package data;

import java.util.Map;
import java.util.Set;

public interface Variables {

    public Class<?> getTypeOf(String name);
    public <TValue> TValue getValue(String name, Class<TValue> type);
    public <TValue> Set<Variable<TValue>> getAllOfType(Class<TValue> type);
    public Variables addIntegers(Map<String, Integer> nameToIntValue);
    public Variables addBooleans(Map<String, Boolean> nameToBoolValue);
    public Variables addDoubles(Map<String, Double> nameToDoubleValue);
    public Variables addLongs(Map<String, Long> nameToLongValue);
    public Variables addStrings(Map<String, String> nameToStringValue);
    public Variables addCharacters(Map<String, Character> nameToCharValue);
    public Variables addObjects(Map<String, Object> nameToObjectValue);
}
