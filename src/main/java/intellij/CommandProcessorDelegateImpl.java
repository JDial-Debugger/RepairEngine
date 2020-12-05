package intellij;

import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;

public class CommandProcessorDelegateImpl implements CommandProcessorDelegate {

	private Project project;
	private CommandProcessor wrappedProcessor;
	private Object solverScriptGroupId;

	private static final String SOLVER_SCRIPT_COMMAND = "Modify Solver Script";

	public CommandProcessorDelegateImpl(Project project) {
		this(project, CommandProcessor.getInstance());
	}

	public CommandProcessorDelegateImpl(Project project, CommandProcessor wrappedProcessor) {
		this.wrappedProcessor = wrappedProcessor;
		this.project = project;
		this.solverScriptGroupId = new Object();
	}

	@Override
	public void executeSolverScriptModification(Runnable toExecute) {
		wrappedProcessor.executeCommand(this.project, toExecute, SOLVER_SCRIPT_COMMAND, this.solverScriptGroupId);
	}
}
