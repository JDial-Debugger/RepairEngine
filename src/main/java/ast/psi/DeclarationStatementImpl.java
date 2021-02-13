package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.DeclarationStatement;
import ast.interfaces.LocalVariable;
import ast.interfaces.Node;
import com.intellij.psi.PsiDeclarationStatement;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLocalVariable;

import java.util.ArrayList;
import java.util.List;

public class DeclarationStatementImpl extends StatementImpl implements DeclarationStatement {
	protected DeclarationStatementImpl(NodeConfig<? extends PsiDeclarationStatement> config) {
		super(config);
	}

	@Override
	public List<LocalVariable> getDeclaredLocalVariables() {
		PsiElement[] declaredDelegates
				= ((PsiDeclarationStatement) this.element).getDeclaredElements();
		List<LocalVariable> declaredNodes = new ArrayList<>();

		for (PsiElement delegate : declaredDelegates) {
			if (delegate instanceof PsiLocalVariable) {
				LocalVariable localVar
						= (LocalVariable) this.delegateStore.getNodeFrom(delegate);
				declaredNodes.add(localVar);
			}
		}
		return declaredNodes;
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitDeclarationStatement(this);
	}
}
