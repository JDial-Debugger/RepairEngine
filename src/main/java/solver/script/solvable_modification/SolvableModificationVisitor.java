package solver.script.solvable_modification;

import ast.interfaces.*;

import java.util.Set;

public class SolvableModificationVisitor extends AstVisitorBase implements SolvableModificationGenerator {

	private SolvableCodeModificationAST ast;
	private Set<SolvableCodeModification> solvableModifications;
	private SolvableModificationIDGenerator idGenerator;

	public SolvableModificationVisitor(
			SolvableCodeModificationAST ast,
			Set<SolvableCodeModification> solvableModifications,
			SolvableModificationIDGenerator idGenerator) {
		this.ast = ast;
		this.solvableModifications = solvableModifications;
		this.idGenerator = idGenerator;
	}

	//  To avoid unnecessary nesting of modifications in nested BinaryExprs, only add a modification to the most top-level expression
	//  Detect the most top-level by a change of types (e.g if current node is int type and parent is boolean, then we have reached top-level)
	@Override
	public void visitBinaryExpression(BinaryExpression expression) {
		if (this.expressionIsTopMost(expression)) {
			this.insertSolvableCode(expression);
		}
		super.visitBinaryExpression(expression);
	}

	private void insertSolvableCode(Expression expressionToSolve) {
		SolvableModificationId id = this.idGenerator.getSolvableModificationId();
		SolvableCodeModification mod = new SolvableCodeModification(expressionToSolve, id);
		Expression solvableCode = this.ast.getSolvableCode(mod);
		expressionToSolve.replace(solvableCode);
		this.solvableModifications.add(mod);
	}

	private boolean expressionIsTopMost(BinaryExpression expression) {
		return this.parentIsDifferentType(expression.getParent())
				&& this.expressionIsCorrectType(expression);
	}

	private boolean parentIsDifferentType(Node parent) {
		if (parent instanceof Expression) {
			return !this.expressionIsCorrectType((Expression) parent);
		}
		return true;
	}

	private boolean expressionIsCorrectType(Expression expression) {
		return expression.getType().asEnum() == PrimitiveType.INT;
	}

	@Override
	public Set<SolvableCodeModification> getSolvableCodeModifications() {
		return this.solvableModifications;
	}
}
