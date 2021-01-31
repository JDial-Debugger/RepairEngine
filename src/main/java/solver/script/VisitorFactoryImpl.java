package solver.script;

import ast.interfaces.Expression;
import ast.psi.NodeFactoryImpl;
import ast.psi.PsiElementExtractorImpl;
import ast.psi.PsiNodeBuilder;
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
	public SolvableModificationVisitor getSolvableModificationVisitor(Expression solverHolePlaceholder) {
		return new SolvableModificationVisitor(
				new SolvableCodeModificationASTImpl(
						new PsiNodeBuilder(
								JavaPsiFacade.getInstance(project).getElementFactory(),
								new CommandProcessorDelegateImpl(project),
								new PsiElementExtractorImpl(),
								new ArrayStringBuilderImpl(),
								new NodeFactoryImpl()),
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
		return null;
	}

	@Override
	public CorrectedVariableRecordVisitor getCorrectedVariableRecordVisitor() {
		return new CorrectedVariableRecordVisitor();
	}
}
