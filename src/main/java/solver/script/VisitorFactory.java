package solver.script;

import solver.script.correction.CorrectedVariableRecordVisitor;
import solver.script.solvable_modification.SolvableModificationVisitor;
import solver.script.state_record.ScopedVariablesVisitor;
import solver.script.state_record.StateRecordVisitor;

public interface VisitorFactory {
	public SolvableModificationVisitor getSolvableModificationVisitor();
	public ScopedVariablesVisitor getScopedVariablesVisitor();
	public StateRecordVisitor getStateRecordVisitor();
	public CorrectedVariableRecordVisitor getCorrectedVariableRecordVisitor();
}
