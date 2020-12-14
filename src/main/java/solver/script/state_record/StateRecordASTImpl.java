package solver.script.state_record;

import ast.interfaces.DeclarationStatementDelegate;
import ast.interfaces.ExpressionStatementDelegate;
import ast.interfaces.NodeFactory;
import ast.interfaces.StatementDelegate;
import data.ProgramTrace;

import java.util.List;

public class StateRecordASTImpl implements  StateRecordAST {

	private NodeFactory nodeFactory;
	private String stateIndexId;

	private static final String ID_PREFIX = "__JDIAL__";
	private static final String ID_SUFFIX = "_state_record";

	public StateRecordASTImpl(NodeFactory nodeFactory, String stateIndexId) {
		this.nodeFactory = nodeFactory;
		this.stateIndexId = stateIndexId;
	}

	@Override
	public ExpressionStatementDelegate getRecordStatement(StateRecord record) {
		return null;
	}

	@Override
	public StatementDelegate getInitializationStatement(
			StateRecord record, List<ProgramTrace> traces) {
		String identifier = this.getIdentifierFrom(record);
		Integer[] dimensions = this.getDimensionsAsTraceLengths(traces);

		return this.nodeFactory.getEmptyArrayDeclaration(record.variableType, identifier, dimensions);
	}

	private String getIdentifierFrom(StateRecord record) {
		return ID_PREFIX + record.functionName + "_" + record.variableName + ID_SUFFIX;
	}

	private Integer[] getDimensionsAsTraceLengths(List<ProgramTrace> traces) {
		Integer[] dimensions = new Integer[traces.size()];
		for (int i = 0; i < traces.size(); ++i) {
			dimensions[i] = traces.get(i).traceUnits.size();
		}
		return dimensions;
	}
}
