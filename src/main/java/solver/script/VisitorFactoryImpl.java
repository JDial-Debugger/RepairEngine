package solver.script;

import ast.interfaces.Expression;
import ast.interfaces.NodeBuilder;
import ast.psi.NodeFactoryImpl;
import ast.psi.PsiElementExtractorImpl;
import ast.psi.PsiNodeBuilder;
import ast.psi.factory.ArrayStringBuilderImpl;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import solver.script.correction.CorrectedVariableRecordVisitor;
import solver.script.solvable_modification.*;
import solver.script.state_record.ScopedVariablesVisitor;
import solver.script.state_record.StateRecordVisitor;

import java.util.HashSet;

public class VisitorFactoryImpl implements VisitorFactory {

	private Project project;

	public VisitorFactoryImpl(Project project) {
		this.project = project;
	}

	@Override
	public SolvableModificationVisitor getSolvableModificationVisitor(Expression solverHolePlaceholder) {
		NodeBuilder builder = new PsiNodeBuilder(
				JavaPsiFacade.getInstance(project).getElementFactory(),
				new PsiElementExtractorImpl(),
				new ArrayStringBuilderImpl(),
				new NodeFactoryImpl(project));

		return new SolvableModificationVisitor(
				new SolvableCodeModificationASTImpl(builder, solverHolePlaceholder),
				new HashSet<>(),
				new SolvableModificationIDGeneratorImpl());
	}

	@Override
	public ScopedVariablesVisitor getScopedVariablesVisitor() {
		return new ScopedVariablesVisitor();
	}

	@Override
	public StateRecordVisitor getStateRecordVisitor() {
		return null;
	}

	@Override
	public CorrectedVariableRecordVisitor getCorrectedVariableRecordVisitor() {
		return new CorrectedVariableRecordVisitor();
	}
}
