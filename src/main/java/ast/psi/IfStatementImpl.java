package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.CodeBlock;
import ast.interfaces.IfStatement;
import ast.interfaces.Statement;
import com.intellij.psi.PsiBlockStatement;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiIfStatement;
import com.intellij.psi.PsiStatement;

public class IfStatementImpl extends StatementImpl implements IfStatement {
	protected IfStatementImpl(NodeConfig<? extends com.intellij.psi.PsiIfStatement> config) {
		super(config);
	}

	@Override
	public Statement getThenBranch() {
		PsiStatement delegate = ((PsiIfStatement) this.element).getThenBranch();
		return (Statement) this.delegateStore.getNodeFrom(delegate);
	}

	@Override
	public Statement getElseBranch() {
		PsiStatement delegate = ((PsiIfStatement) this.element).getThenBranch();
		return (Statement) this.delegateStore.getNodeFrom(delegate);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitIfStatement(this);
	}
}
