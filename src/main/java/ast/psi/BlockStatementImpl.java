package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.BlockStatement;
import ast.interfaces.CodeBlock;
import com.intellij.psi.PsiBlockStatement;
import com.intellij.psi.PsiCodeBlock;

public class BlockStatementImpl extends StatementImpl implements BlockStatement {

	protected BlockStatementImpl(NodeConfig<? extends PsiBlockStatement> config) {
		super(config);
	}

	@Override
	public CodeBlock getCodeBlock() {
		PsiCodeBlock delegate = ((PsiBlockStatement) this.element).getCodeBlock();
		return (CodeBlock) this.delegateStore.getNodeFrom(delegate);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitBlockStatement(this);
	}
}
