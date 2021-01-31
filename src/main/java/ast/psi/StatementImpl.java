package ast.psi;

import ast.interfaces.Statement;
import ast.interfaces.AstVisitor;

public class StatementImpl extends NodeImplBase implements Statement {

	protected StatementImpl(NodeConfig<? extends com.intellij.psi.PsiStatement> config) {
		super(config);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitStatement(this);
	}

	@Override
	public Statement addAfter(Statement statement) {
		com.intellij.psi.PsiStatement delegate = (com.intellij.psi.PsiStatement) this.element;
		com.intellij.psi.PsiStatement statementToAdd = this.extractor.getDelegateElement(
				com.intellij.psi.PsiStatement.class,
				statement);
		delegate = (com.intellij.psi.PsiStatement) delegate.getParent().addAfter(statementToAdd, delegate);
		return (Statement) this.delegateStore.getNodeFrom(delegate);
	}
}
