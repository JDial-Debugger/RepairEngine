package ast.psi;

import ast.interfaces.StatementDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiStatement;

public class PsiStatementDelegate extends PsiNodeDelegateBase implements StatementDelegate {

	protected PsiStatementDelegate(NodeConfig<? extends PsiStatement> config) {
		super(config);
	}

	@Override
	public void accept(VisitorDelegate visitor) {
		visitor.visitStatement(this);
	}

	@Override
	public StatementDelegate addAfter(StatementDelegate statement) {
		PsiStatement delegate = (PsiStatement) this.element;
		PsiStatement statementToAdd = this.extractor.getDelegateElement(PsiStatement.class,
				statement);
		delegate = (PsiStatement) delegate.getParent().addAfter(statementToAdd, delegate);
		return (StatementDelegate) this.delegateStore.getNodeFrom(delegate);
	}
}
