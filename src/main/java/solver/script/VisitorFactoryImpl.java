package solver.script;

import ast.interfaces.ExpressionDelegate;
import ast.psi.PsiElementExtractorImpl;
import ast.psi.PsiNodeFactory;
import ast.psi.factory.ArrayStringBuilderImpl;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import intellij.CommandProcessorDelegateImpl;
import solver.script.correction.CorrectedVariableRecordVisitor;
import solver.script.solvable_modification.*;
import solver.script.state_record.ScopedVariablesVisitor;
import solver.script.state_record.StateRecordVisitor;

import java.util.HashSet;

public class VisitorFactoryImpl implements  VisitorFactory{

	private Project project;
	public VisitorFactoryImpl(Project project) {
		this.project = project;
	}

	@Override
	public SolvableModificationVisitor getSolvableModificationVisitor(ExpressionDelegate solverHolePlaceholder) {
		return new SolvableModificationVisitor(
				new SolvableCodeModificationASTImpl(
						new PsiNodeFactory(
								JavaPsiFacade.getInstance(project).getElementFactory(),
								new CommandProcessorDelegateImpl(project),
								new PsiElementExtractorImpl(),
								new ArrayStringBuilderImpl()),
						solverHolePlaceholder),
				new HashSet<SolvableCodeModification>(),
				new SolvableModificationIDGeneratorImpl());
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
