package solver.repair;

import ast.interfaces.File;
import ast.interfaces.NodeBuilder;
import solver.script.SolverScriptGenerator;

import java.util.Map;

public class ASTRepairImpl implements ASTRepair {

	private SolverScriptGenerator solverScriptGenerator;
	private SolverRepository solverRepository;
	private RawSolverReader rawSolverReader;
	private SolvedModificationVisitor solvedModificationVisitor;
	private NodeBuilder nodeBuilder;

	public ASTRepairImpl(
			SolverScriptGenerator solverScriptGenerator,
			SolverRepository solverRepository,
			RawSolverReader rawSolverReader,
			SolvedModificationVisitor solvedModificationVisitor,
			NodeBuilder nodeBuilder
	) {
		this.solverScriptGenerator = solverScriptGenerator;
		this.solverRepository = solverRepository;
		this.rawSolverReader = rawSolverReader;
		this.solvedModificationVisitor = solvedModificationVisitor;
		this.nodeBuilder = nodeBuilder;
	}

	@Override
	public Map<Integer, String> getModifiedLines(File fileDelegate) {
		return null;
	}
}
