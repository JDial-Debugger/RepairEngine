package raw_trace;

import data.Variables;

import java.util.Map;

public interface RawVariableParser {
    public Variables parseRawVariables(Map<String, String> rawVariables);
}
