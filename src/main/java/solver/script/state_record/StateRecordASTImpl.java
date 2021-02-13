package solver.script.state_record;

import ast.interfaces.NodeBuilder;
import ast.interfaces.Statement;
import data.ProgramTrace;

import java.util.List;

public class StateRecordASTImpl implements StateRecordAST {

	private NodeBuilder nodeBuilder;
	private static final String STATE_INDEX_ID = "__JDIAL__STATE_IDX";
	private static final String EXAMPLE_INDEX_ID = "__JDIAL_EXAMPLE_IDX";
	private StateRecordIDGenerator idGenerator;

	public StateRecordASTImpl(
			NodeBuilder nodeBuilder,
			StateRecordIDGenerator idGenerator) {
		this.nodeBuilder = nodeBuilder;
		this.idGenerator = idGenerator;
	}

	//  Example:
	//  __JDIAL__func_var__state_record[__JDIAL__example_idx][__JDIAL__state_idx] = var;
	@Override
	public Statement getRecordStatement(StateRecord record) {
		return this.nodeBuilder.buildStatementFromText(this.idGenerator.getId(record.functionName,
				record.variableName)
				+ "["
				+ EXAMPLE_INDEX_ID
				+ "]["
				+ STATE_INDEX_ID
				+ "] = "
				+ record.variableName
				+ ";");
	}


	@Override
	public Statement getInitializationStatement(
			StateRecord record, List<ProgramTrace> traces) {
		String identifier = this.idGenerator.getId(record.variableName, record.functionName);
		Integer[] dimensions = this.getDimensionsAsTraceLengths(traces);

		return this.nodeBuilder.buildEmptyArrayDeclaration(record.variableType,
				identifier,
				dimensions);
	}

	private Integer[] getDimensionsAsTraceLengths(List<ProgramTrace> traces) {
		Integer[] dimensions = new Integer[traces.size()];
		for (int i = 0; i < traces.size(); ++i) {
			dimensions[i] = traces.get(i).traceUnits.size();
		}
		return dimensions;
	}
}
