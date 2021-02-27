package ast.psi;

import ast.interfaces.AstVisitor;
import ast.interfaces.Expression;
import com.intellij.psi.PsiArrayAccessExpression;
import com.intellij.psi.PsiExpression;

public class ArrayAccessExpressionImpl extends ExpressionImpl implements ast.interfaces.ArrayAccessExpression {
	protected ArrayAccessExpressionImpl(NodeConfig<? extends PsiArrayAccessExpression> config) {
		super(config);
	}

	@Override
	public Expression getArray() {
		PsiExpression delegate = ((PsiArrayAccessExpression) this.element).getArrayExpression();
		return (Expression) this.delegateStore.getNodeFrom(delegate);
	}

	@Override
	public Expression getIndex() {
		PsiExpression delegate = ((PsiArrayAccessExpression) this.element).getIndexExpression();
		return (Expression) this.delegateStore.getNodeFrom(delegate);
	}

	@Override
	public void accept(AstVisitor astVisitor) {
		astVisitor.visitArrayAccessExpression(this);
	}
}
