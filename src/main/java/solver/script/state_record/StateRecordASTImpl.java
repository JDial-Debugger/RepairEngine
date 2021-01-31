package solver.script.state_record;

import ast.interfaces.NodeBuilder;
import ast.interfaces.Statement;
import data.ProgramTrace;

import java.util.List;

public class StateRecordASTImpl implements StateRecordAST {

	private NodeBuilder nodeBuilder;
	private String stateIndexId;
	private String exampleIndexId;

	private static final String ID_PREFIX = "__JDIAL__";
	private static final String ID_SUFFIX = "__state_record";

	public StateRecordASTImpl(NodeBuilder nodeBuilder, String stateIndexId, String exampleIndexId) {
		this.nodeBuilder = nodeBuilder;
		this.stateIndexId = stateIndexId;
		this.exampleIndexId = exampleIndexId;
	}

	// __JDIAL__func_var__state_record[__JDIAL__example_idx][__JDIAL__state_idx] = var;
	@Override
	public Statement getRecordStatement(StateRecord record) {
		return this.nodeBuilder.buildStatementFromText(
				getIdentifierFrom(record)
				+ "["
				+ this.exampleIndexId
				+ "]["
				+ this.stateIndexId
				+ "] = "
				+ record.variableName
				+ ";");
	}

	private String getIdentifierFrom(StateRecord record) {
		return ID_PREFIX + record.functionName + "__" + record.variableName + ID_SUFFIX;
	}

	@Override
	public Statement getInitializationStatement(
			StateRecord record, List<ProgramTrace> traces) {
		String identifier = this.getIdentifierFrom(record);
		Integer[] dimensions = this.getDimensionsAsTraceLengths(traces);

		return this.nodeBuilder.buildEmptyArrayDeclaration(
				record.variableType,
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
