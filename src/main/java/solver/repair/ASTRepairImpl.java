package solver.repair;

import ast.FileDelegate;
import ast.NodeFactory;
import solver.script.SolverScriptGenerator;

import java.util.Map;

public class ASTRepairImpl implements ASTRepair {

	private SolverScriptGenerator solverScriptGenerator;
	private SolverRepository solverRepository;
	private RawSolverReader rawSolverReader;
	private SolvedModificationVisitor solvedModificationVisitor;
	private NodeFactory nodeFactory;

	public ASTRepairImpl(
			SolverScriptGenerator solverScriptGenerator,
			SolverRepository solverRepository,
			RawSolverReader rawSolverReader,
			SolvedModificationVisitor solvedModificationVisitor,
			NodeFactory nodeFactory
	) {
		this.solverScriptGenerator = solverScriptGenerator;
		this.solverRepository = solverRepository;
		this.rawSolverReader = rawSolverReader;
		this.solvedModificationVisitor = solvedModificationVisitor;
		this.nodeFactory = nodeFactory;
	}

	@Override
	public Map<Integer, String> getModifiedLines(FileDelegate fileDelegate) {
		return null;
	}
}
