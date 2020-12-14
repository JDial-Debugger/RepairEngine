package data;

public class WrongVariableTypeException extends  RuntimeException {
	public WrongVariableTypeException(Class<?> expectedClass, Class<?> actualClass) {
		super("Tried to retrieve value for variable of type " + actualClass + " when expecting value of type " + expectedClass);
	}

}
