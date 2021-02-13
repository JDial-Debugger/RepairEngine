package solver.script.solvable_modification;

public class SolvableModificationIDGeneratorImpl implements SolvableModificationIDGenerator {

	private static int ID_COUNT = 0;
	private static final String CHANGE_VAR_PREFIX = "__JDIAL__change_coeff";
	private static final String FUNC_PREFIX = "__JDIAL__coeff";


	public SolvableModificationIDGeneratorImpl() {
	}

	@Override
	public SolvableModificationId getSolvableModificationId() {
		return new SolvableModificationId(FUNC_PREFIX + ++ID_COUNT, CHANGE_VAR_PREFIX + ID_COUNT);
	}

	@Override
	public SolvableModificationRegexes getSolvableModificationRegexes() {
		return null;
	}
}
