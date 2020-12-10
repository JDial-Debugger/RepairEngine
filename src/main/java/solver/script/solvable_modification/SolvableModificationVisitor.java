package solver.script.solvable_modification;

import ast.interfaces.*;
import com.intellij.ide.ui.EditorOptionsTopHitProvider;
import solver.repair.SolvedModificationsAST;

import java.util.Set;

public class SolvableModificationVisitor extends VisitorDelegateBase {

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
	public void visitBinaryExpression(BinaryExpressionDelegate expression) {
		if (this.expressionIsTopMost(expression)) {
			this.insertSolvableCode(expression);
		}
		super.visitBinaryExpression(expression);
	}

	private void insertSolvableCode(ExpressionDelegate expressionToSolve) {
		SolvableModificationId id = this.idGenerator.getSolvableModificationId();
		SolvableCodeModification mod = new SolvableCodeModification(expressionToSolve, id);
		ExpressionDelegate solvableCode = this.ast.getSolvableCode(mod);
		expressionToSolve.replace(solvableCode);
		this.solvableModifications.add(mod);
	}

	private boolean expressionIsTopMost(BinaryExpressionDelegate expression) {
		return this.parentIsDifferentType(expression.getParent())
				&& this.expressionIsCorrectType(expression);
	}

	private boolean parentIsDifferentType(NodeDelegate parent) {
		if (parent instanceof ExpressionDelegate) {
			return !this.expressionIsCorrectType((ExpressionDelegate) parent);
		}
		return true;
	}

	private boolean expressionIsCorrectType(ExpressionDelegate expression) {
		return expression.getType().asEnum() == Type.INT;
	}

}
