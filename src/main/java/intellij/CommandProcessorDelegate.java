package intellij;

import com.intellij.openapi.project.Project;

public interface CommandProcessorDelegate {

	public void executeSolverScriptModification(Runnable toExecute);
}
