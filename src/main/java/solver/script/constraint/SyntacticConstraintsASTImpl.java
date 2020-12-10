package solver.script.constraint;

import ast.interfaces.NodeFactory;
import solver.script.solvable_modification.SolvableCodeModificationAST;
import solver.script.solvable_modification.SolvableModificationId;

public class SyntacticConstraintsASTImpl implements  SyntacticConstraintsAST {

	private NodeFactory nodeFactory;
	private SolvableCodeModificationAST solvableModificationAST;

	public SyntacticConstraintsASTImpl(NodeFactory nodeFactory, SolvableCodeModificationAST solvableModificationAST) {
		this.nodeFactory = nodeFactory;
		this.solvableModificationAST = solvableModificationAST;
	}

	@Override
	public void addSolvableModificationConstraint(SolvableModificationId solvableModificationId) {

	}
}
