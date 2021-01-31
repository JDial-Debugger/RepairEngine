package ast.psi;

import ast.interfaces.Expression;
import ast.interfaces.Type;
import ast.interfaces.AstVisitor;

public class PsiExpression extends PsiNodeBase implements Expression {

	protected PsiExpression(NodeConfig<? extends com.intellij.psi.PsiExpression> config) {
		super(config);
	}

	protected com.intellij.psi.PsiExpression getWrappedExpression() {
		return (com.intellij.psi.PsiExpression) super.element;
	}

	@Override
	public Type getType() {
		return new PsiType(((com.intellij.psi.PsiExpression) this.element).getType());
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitExpression(this);
	}
}
