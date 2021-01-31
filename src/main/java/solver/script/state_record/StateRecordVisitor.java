package solver.script.state_record;

import ast.interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StateRecordVisitor extends AstVisitorBase {
	private StateRecordAST ast;
	private Stack<List<RecordedVariable>> scopedVariables;

	public StateRecordVisitor(StateRecordAST ast) {
		this.ast = ast;
	}

	@Override
	public void visitStatement(Statement statement) {
		super.visitStatement(statement);

	}

	@Override
	public void visitMethod(Method method) {
		List<RecordedVariable> baseScopeVariables = new ArrayList<>();
		for (Parameter param : method.getParameterList().getParameters()) {
			baseScopeVariables.add(new RecordedVariable(param.getName(), param.getType()));
		}
		super.visitMethod(method);
	}

	private void visitCodeBlock(CodeBlock codeBlock) {

		List<RecordedVariable> curScopeVariables = new ArrayList<>();
		for (Statement statement : codeBlock) {

		}


	}
}
