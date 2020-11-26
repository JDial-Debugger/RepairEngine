package solver.script;

import solver.script.correction.CorrectedVariableRecordVisitor;
import solver.script.solvable_modification.SolvableCodeModification;
import solver.script.solvable_modification.SolvableModificationVisitor;
import solver.script.state_record.ScopedVariablesVisitor;
import solver.script.state_record.StateRecordVisitor;

import java.util.HashSet;

public class VisitorFactoryImpl implements  VisitorFactory{

	public VisitorFactoryImpl() {
	}

	@Override
	public SolvableModificationVisitor getSolvableModificationVisitor() {
		return new SolvableModificationVisitor(new HashSet<SolvableCodeModification>());
	}

	@Override
	public ScopedVariablesVisitor getScopedVariablesVisitor() {
		return new ScopedVariablesVisitor();
	}

	@Override
	public StateRecordVisitor getStateRecordVisitor() {
		return new StateRecordVisitor();
	}

	@Override
	public CorrectedVariableRecordVisitor getCorrectedVariableRecordVisitor() {
		return new CorrectedVariableRecordVisitor();
	}
}
