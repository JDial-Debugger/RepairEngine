package solver.script.constraint;

import ast.interfaces.*;
import com.intellij.psi.PsiUnaryExpression;
import solver.script.solvable_modification.SolvableCodeModificationAST;
import solver.script.solvable_modification.SolvableModificationId;

import java.util.ArrayList;
import java.util.List;

public class SyntacticConstraintsASTImpl implements SyntacticConstraintsAST {

	private NodeBuilder nodeBuilder;
	private SolvableCodeModificationAST solvableModificationAST;

	private static final String ID = "__JDIAL__syntactic_distance";

	public SyntacticConstraintsASTImpl(
			NodeBuilder nodeBuilder,
			SolvableCodeModificationAST solvableModificationAST) {
		this.nodeBuilder = nodeBuilder;
		this.solvableModificationAST = solvableModificationAST;
	}

	@Override
	public Expression getConstraintReferenceExpression() {
		return this.nodeBuilder.buildExpressionFromText(ID);
	}

	@Override
	public List<Statement> getInitializationStatements(List<SolvableModificationId> modificationIds) {
		List<Statement> initializationStatements = new ArrayList<>();

		this.addDistanceDeclarationTo(initializationStatements);

		this.addChangeVarAddStatementsTo(initializationStatements, modificationIds);

		return initializationStatements;
	}

	private void addDistanceDeclarationTo(List<Statement> initializationStatements) {
		Type type = this.nodeBuilder.buildType(PrimitiveType.INT);
		Expression initializer = this.nodeBuilder.buildLiteralIntExpression(0);
		Statement declaration = this.nodeBuilder.buildDeclarationStatement(ID, type, initializer);
		initializationStatements.add(declaration);
	}

	private void addChangeVarAddStatementsTo(List<Statement> initializationStatements, List<SolvableModificationId> modificationIds) {
		for (SolvableModificationId id : modificationIds) {
			Expression changeVarExpression
					= this.nodeBuilder.buildExpressionFromText(id.changeVariable);
			Expression addChangeVarExpr = this.nodeBuilder.buildAssignExpression(
					this.getConstraintReferenceExpression(),
					AssignOperator.ADD,
					changeVarExpression);
			initializationStatements.add(this.nodeBuilder.buildExpressionStatement(addChangeVarExpr));
		}
	}
}
