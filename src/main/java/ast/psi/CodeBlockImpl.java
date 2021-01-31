package ast.psi;

import ast.interfaces.CodeBlock;
import ast.interfaces.Statement;
import com.intellij.psi.PsiStatement;

public class CodeBlockImpl extends NodeImplBase implements CodeBlock {

	protected CodeBlockImpl(NodeConfig<? extends com.intellij.psi.PsiCodeBlock> config) {
		super(config);
	}

	@Override
	public void addStatement(Statement statement) {
		PsiStatement wrappedStatement = super.extractor.getDelegateElement(PsiStatement.class,
				statement);
		((com.intellij.psi.PsiCodeBlock) this.element).add(wrappedStatement);
	}

	@Override
	public void addStatements(Statement... statements) {
		for (Statement stmt : statements) {
			this.addStatement(stmt);
		}
	}
}
