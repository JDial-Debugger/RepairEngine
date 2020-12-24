package solver.script.state_record;

import ast.interfaces.StatementDelegate;
import ast.interfaces.VisitorDelegateBase;
import com.intellij.psi.PsiStatement;

public class StateRecordVisitor extends VisitorDelegateBase {
	private StateRecordAST ast;

	public StateRecordVisitor(StateRecordAST ast) {
		this.ast = ast;
	}

	@Override
	public void visitStatement(StatementDelegate statement) {
		super.visitStatement(statement);

	}
}
