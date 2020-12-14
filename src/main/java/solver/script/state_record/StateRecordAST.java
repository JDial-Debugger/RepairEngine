package solver.script.state_record;

import ast.interfaces.DeclarationStatementDelegate;
import ast.interfaces.ExpressionStatementDelegate;
import ast.interfaces.StatementDelegate;
import data.ProgramTrace;

import java.util.List;

public interface StateRecordAST {
	public StatementDelegate getRecordStatement(StateRecord record);
	public StatementDelegate getInitializationStatement(StateRecord record, List<ProgramTrace> traces);
}
