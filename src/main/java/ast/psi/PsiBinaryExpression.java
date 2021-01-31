package ast.psi;

import ast.interfaces.BinaryExpression;
import ast.interfaces.AstVisitor;

public class PsiBinaryExpression extends PsiExpression
		implements BinaryExpression {

	protected PsiBinaryExpression(NodeConfig<? extends com.intellij.psi.PsiBinaryExpression> config) {
		super(config);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitBinaryExpression(this);
	}
}
