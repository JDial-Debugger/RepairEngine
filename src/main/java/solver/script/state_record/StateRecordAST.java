package solver.script.state_record;

import ast.interfaces.Statement;
import data.ProgramTrace;

import java.util.List;

public interface StateRecordAST {
	public Statement getRecordStatement(StateRecord record);
	public Statement getInitializationStatement(StateRecord record, List<ProgramTrace> traces);
}
