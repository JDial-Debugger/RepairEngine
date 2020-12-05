package ast;

import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiStatement;

public class PsiCodeBlockDelegate extends PsiNodeDelegate implements CodeBlockDelegate {

	protected PsiCodeBlockDelegate(
			PsiCodeBlock element) {
		super(element);
	}

	@Override
	public void addStatement(StatementDelegate statement) {
		PsiStatement wrappedStatement = super.extractor.getWrappedElement(PsiStatement.class, statement);
		((PsiCodeBlock) this.element).add(wrappedStatement);
	}

	@Override
	public void addStatements(StatementDelegate... statements) {
		for (StatementDelegate stmt : statements) {
			this.addStatement(stmt);
		}
	}
}
