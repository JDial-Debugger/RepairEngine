package raw_trace.data;

import data.Event;

import java.util.List;
import java.util.Map;

public class RawTraceUnit {
    public String stdout;
    public Event event;
    public int lineNumber;
    public List<RawFrame> stack;
    public String inScopeFuncName;
    public Map<String, String> correctedVariables;
}
