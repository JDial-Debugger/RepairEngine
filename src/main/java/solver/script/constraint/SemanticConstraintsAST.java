package solver.script.constraint;

import ast.interfaces.Statement;
import solver.script.state_record.StateRecord;

public interface SemanticConstraintsAST extends MinimizableConstraintAST {
	Statement getStateRecordChangedConstraint(
			StateRecord stateRecord,
			StateRecord originalStateRecord);
}
