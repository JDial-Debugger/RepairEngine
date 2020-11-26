package solver.script.constraint;

import solver.script.state_record.StateRecord;

public interface SemanticConstraintsAST extends MinimizableConstraintAST {
	public void addStateRecordChangedConstraint(StateRecord stateRecord, StateRecord originalStateRecord);
}
