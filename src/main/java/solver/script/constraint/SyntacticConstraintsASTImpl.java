package solver.script.constraint;

import ast.interfaces.NodeBuilder;
import solver.script.solvable_modification.SolvableCodeModificationAST;
import solver.script.solvable_modification.SolvableModificationId;

public class SyntacticConstraintsASTImpl implements  SyntacticConstraintsAST {

	private NodeBuilder nodeBuilder;
	private SolvableCodeModificationAST solvableModificationAST;

	public SyntacticConstraintsASTImpl(NodeBuilder nodeBuilder, SolvableCodeModificationAST solvableModificationAST) {
		this.nodeBuilder = nodeBuilder;
		this.solvableModificationAST = solvableModificationAST;
	}

	@Override
	public void addSolvableModificationConstraint(SolvableModificationId solvableModificationId) {

	}
}
