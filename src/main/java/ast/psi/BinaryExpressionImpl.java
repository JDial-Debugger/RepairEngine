package ast.psi;

import ast.interfaces.BinaryExpression;
import ast.interfaces.AstVisitor;

public class BinaryExpressionImpl extends ExpressionImpl
		implements BinaryExpression {

	protected BinaryExpressionImpl(NodeConfig<? extends com.intellij.psi.PsiBinaryExpression> config) {
		super(config);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitBinaryExpression(this);
	}
}
