package data;

import java.util.Set;

public interface Variables {

    public Class<?> getTypeOf(String name);
    public <TValue> TValue getValue(String name, Class<TValue> type);
    public <TValue> Set<Variable<TValue>> getAllOfType(Class<TValue> type);
}
