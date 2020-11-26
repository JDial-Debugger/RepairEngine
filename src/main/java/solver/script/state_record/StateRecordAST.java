package solver.script.state_record;

import ast.DeclarationStatementDelegate;
import ast.ExpressionStatementDelegate;
import data.ProgramTrace;

import java.util.List;

public interface StateRecordAST {
	public ExpressionStatementDelegate getRecordStatement(StateRecord record);
	public DeclarationStatementDelegate getInitializationStatement(StateRecord record, List<ProgramTrace> traces);
}
