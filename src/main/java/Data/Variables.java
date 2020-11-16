package Data;

import java.util.Set;

public interface Variables {
    public Class<?> getTypeOf(String name);

    public int getIntegerValue(String name);
    public double getDoubleValue(String name);
    public long getLongValue(String name);
    public boolean getBooleanValue(String name);
    public String getStringValue(String name);
    public char getCharValue(String name);
    public Object getCustomObjectValue(String name);

    public Set<Variable<Integer>> getAllIntegers();
    public Set<Variable<Double>> getAllDoubles();
    public Set<Variable<Long>> getAllLongs();
    public Set<Variable<Boolean>> getAllBooleans();
    public Set<Variable<String>> getAllStrings();
    public Set<Variable<Character>> getAllChars();
    public Set<Variable<Object>> getAllCustomObjects();
    public Set<Variable<?>> getAllVariables();
}
