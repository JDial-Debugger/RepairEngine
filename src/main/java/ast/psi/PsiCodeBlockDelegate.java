package ast.psi;

import ast.interfaces.CodeBlockDelegate;
import ast.interfaces.StatementDelegate;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiStatement;

public class PsiCodeBlockDelegate extends PsiNodeDelegateBase implements CodeBlockDelegate {

	protected PsiCodeBlockDelegate(NodeConfig<? extends PsiCodeBlock> config) {
		super(config);
	}

	@Override
	public void addStatement(StatementDelegate statement) {
		PsiStatement wrappedStatement = super.extractor.getDelegateElement(PsiStatement.class,
				statement);
		((PsiCodeBlock) this.element).add(wrappedStatement);
	}

	@Override
	public void addStatements(StatementDelegate... statements) {
		for (StatementDelegate stmt : statements) {
			this.addStatement(stmt);
		}
	}
}
