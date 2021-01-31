package solver.script.state_record;

import ast.interfaces.Statement;
import ast.interfaces.AstVisitorBase;

public class StateRecordVisitor extends AstVisitorBase {
	private StateRecordAST ast;

	public StateRecordVisitor(StateRecordAST ast) {
		this.ast = ast;
	}

	@Override
	public void visitStatement(Statement statement) {
		super.visitStatement(statement);

	}
}
