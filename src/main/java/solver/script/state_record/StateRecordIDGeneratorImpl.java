package solver.script.state_record;

public class StateRecordIDGeneratorImpl implements StateRecordIDGenerator {
	private static final String PREFIX = "__JDIAL__";
	private static final String STATE_SUFFIX = "__STATE_RECORD";

	@Override
	public String getId(String varName, String funcName) {
		return PREFIX + varName + "_" + funcName + STATE_SUFFIX;
	}
}
