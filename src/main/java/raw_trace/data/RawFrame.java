package raw_trace.data;

import java.util.List;
import java.util.Map;

public class RawFrame {
    public String name;
    public Map<Integer, String> encodedLocals;
    public List<String> orderedVarNames;
    public boolean isHighlighted;
    public boolean isZombie;
    public int id;
}
