package Data;

public class NoSuchVariableException extends RuntimeException {

    public NoSuchVariableException(String varName, Class<?> varType) {
        super("No variables exists with name " + varName + " and type " + varType);
    }
}
