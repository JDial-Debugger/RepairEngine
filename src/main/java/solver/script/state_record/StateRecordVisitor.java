package solver.script.state_record;

import ast.interfaces.Statement;
import ast.interfaces.AstVisitorDelegateBase;

public class StateRecordVisitor extends AstVisitorDelegateBase {
	private StateRecordAST ast;

	public StateRecordVisitor(StateRecordAST ast) {
		this.ast = ast;
	}

	@Override
	public void visitStatement(Statement statement) {
		super.visitStatement(statement);

	}
}
