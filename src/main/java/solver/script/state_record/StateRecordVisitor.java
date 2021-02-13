package solver.script.state_record;

import ast.interfaces.*;
import factory.java.util.ListFactory;
import factory.java.util.StackFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StateRecordVisitor extends AstVisitorBase {
	private StateRecordAST ast;
	private Stack<List<StateRecord>> scopedVariables;
	private String curMethodName;
	private CodeBlock curVisitingCodeBlock;
	private ListFactory listFactory;
	private StackFactory stackFactory;

	public StateRecordVisitor(
			StateRecordAST ast,
			ListFactory listFactory,
			StackFactory stackFactory) {
		this.ast = ast;
		this.listFactory = listFactory;
		this.stackFactory = stackFactory;
	}

	@Override
	public void visitMethod(Method method) {
		this.curMethodName = method.getName();

		this.resetScopedVariables();
		this.addParamsToCurrentScope(method);

		super.visitMethod(method);
	}

	private void resetScopedVariables() {
		this.scopedVariables = this.stackFactory.createStack();
		this.scopedVariables.push(this.listFactory.createEmptyArrayList());
	}

	private void addParamsToCurrentScope(Method method) {
		for (Parameter param : method.getParameterList().getParameters()) {
			StateRecord paramRecord = new StateRecord(
					param.getName(),
					this.curMethodName,
					param.getType());
			this.scopedVariables.peek().add(paramRecord);
		}
	}

	@Override
	public void visitCodeBlock(CodeBlock codeBlock) {
		this.curVisitingCodeBlock = codeBlock;
		this.scopedVariables.push(this.listFactory.createEmptyArrayList());
		this.insertRecordStatementsInCodeBlock(codeBlock);
		this.scopedVariables.pop();
	}

	private void insertRecordStatementsInCodeBlock(CodeBlock codeBlockToInsertInto) {
		for (Statement statement : codeBlockToInsertInto.getStatements()) {
			if (statement instanceof DeclarationStatement) {
				this.addVarsFromDeclaration((DeclarationStatement) statement);
			}
			if (!(statement instanceof ReturnStatement)) {
				this.insertAvailableRecordStatementsAfter(statement);
			}
		}
	}

	private void addVarsFromDeclaration(DeclarationStatement declStatement) {
		List<LocalVariable> declaredVariables = declStatement.getDeclaredLocalVariables();
		for (LocalVariable var : declaredVariables) {
			StateRecord record = new StateRecord(var.getName(), curMethodName, var.getType());
			this.scopedVariables.peek().add(record);
		}
	}

	private void insertAvailableRecordStatementsAfter(Statement anchor) {
		for (StateRecord availableRecord : this.getAllCurrentlyScopedRecords()) {
			Statement assignVarStateStmt = this.ast.getRecordStatement(availableRecord);
			this.curVisitingCodeBlock.addAfter(assignVarStateStmt, anchor);
		}
	}

	private List<StateRecord> getAllCurrentlyScopedRecords() {
		List<StateRecord> records = this.listFactory.createEmptyArrayList();
		for (List<StateRecord> scope : this.scopedVariables) {
			records.addAll(scope);
		}
		return records;
	}
}
