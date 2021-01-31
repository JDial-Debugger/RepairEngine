package solver.script.state_record;

import ast.interfaces.Method;
import ast.interfaces.Statement;
import ast.interfaces.AstVisitorBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StateRecordVisitor extends AstVisitorBase {
	private StateRecordAST ast;

	public StateRecordVisitor(StateRecordAST ast) {
		this.ast = ast;
	}

	@Override
	public void visitStatement(Statement statement) {
		super.visitStatement(statement);

	}

	@Override
	public void visitMethod(Method method) {
		Stack<List<String>> scopedVarNames = new Stack<List<String>>();
		List<String> baseScopeVarNames = new ArrayList<>();
		method.get
		for ()
		super.visitMethod(method);
	}
}
