package ast.interfaces;

public interface IfStatement extends Statement {
	Statement getThenBranch();
	Statement getElseBranch();
}
