package data;

import java.util.Objects;

public class Variable<TValue> {
    public String name;
    public TValue value;

    public Variable(String name, TValue value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Variable<?> variable = (Variable<?>) o;
        return Objects.equals(name, variable.name) && Objects.equals(value, variable.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

}
