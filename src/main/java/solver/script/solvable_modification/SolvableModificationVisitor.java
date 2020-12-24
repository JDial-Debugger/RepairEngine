package solver.script.solvable_modification;

import ast.interfaces.*;

import java.util.Set;

public class SolvableModificationVisitor extends VisitorDelegateBase implements SolvableModificationGenerator {

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

	//  To avoid unnecessary nesting of modifications in nested BinaryExpression,
	//  only add a modification to the most top-level expression
	//  Detect the most top-level by a change of types
	//  (e.g if current node is int type and parent is boolean, then we have reached top-level)
	@Override
	public void visitBinaryExpression(BinaryExpressionDelegate expression) {
		if (this.expressionIsTopMost(expression)) {
			this.insertSolvableCode(expression);
		}
		super.visitBinaryExpression(expression);
	}

	@Override
	public void visitUnaryExpression(UnaryExpressionDelegate expression) {
		if (this.expressionIsTopMost(expression)) {
			this.insertSolvableCode(expression);
		}
		super.visitUnaryExpression(expression);
	}

	@Override
	public void visitAssignExpression(AssignExpressionDelegate expression) {
		if (this.expressionIsTopMost(expression)) {
			this.insertSolvableCode(expression);
		}
		super.visitAssignExpression(expression);
	}

	@Override
	public void visitLiteralExpression(LiteralExpressionDelegate expression) {
		if (this.expressionIsTopMost(expression)) {
			this.insertSolvableCode(expression);
		}
		super.visitLiteralExpression(expression);
	}

	@Override
	public void visitCallExpression(CallExpressionDelegate expression) {
		if (this.expressionIsTopMost(expression)) {
			this.insertSolvableCode(expression);
		}
		super.visitCallExpression(expression);
	}

	@Override
	public void visitLambdaExpression(LambdaExpressionDelegate expression) {
		if (this.expressionIsTopMost(expression)) {
			this.insertSolvableCode(expression);
		}
		super.visitLambdaExpression(expression);
	}

	private void insertSolvableCode(ExpressionDelegate expressionToSolve) {
		SolvableModificationId id = this.idGenerator.getSolvableModificationId();
		SolvableCodeModification mod = new SolvableCodeModification(expressionToSolve, id);
		ExpressionDelegate solvableCode = this.ast.getSolvableCode(mod);
		expressionToSolve.replace(solvableCode);
		this.solvableModifications.add(mod);
	}

	/**
	 * Verify if the expression is the top most, for example (...) + (....), the top most of this expression is an ADD.
	 * This method works for all types of expressions
	 * @param expression ExpressionDelegate that is passed in to verify
	 * @return true if the expression is top most; false otherwise
	 */
	private boolean expressionIsTopMost(ExpressionDelegate expression) {
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

	@Override
	public Set<SolvableCodeModification> getSolvableCodeModifications() {
		return this.solvableModifications;
	}
}
