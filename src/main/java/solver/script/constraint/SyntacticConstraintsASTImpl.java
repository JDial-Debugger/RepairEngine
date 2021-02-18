package solver.script.constraint;

import ast.interfaces.Expression;
import ast.interfaces.NodeBuilder;
import ast.interfaces.Statement;
import solver.script.solvable_modification.SolvableCodeModificationAST;
import solver.script.solvable_modification.SolvableModificationId;

import java.util.List;

public class SyntacticConstraintsASTImpl implements SyntacticConstraintsAST {

	private NodeBuilder nodeBuilder;
	private SolvableCodeModificationAST solvableModificationAST;

	public SyntacticConstraintsASTImpl(NodeBuilder nodeBuilder, SolvableCodeModificationAST solvableModificationAST) {
		this.nodeBuilder = nodeBuilder;
		this.solvableModificationAST = solvableModificationAST;
	}

	@Override
	public void addSolvableModificationConstraint(SolvableModificationId solvableModificationId) {

	}

	@Override
	public Expression getReferenceExpression() {
		return null;
	}

	@Override
	public List<Statement> getInitializationStatements() {
		return null;
	}
}
