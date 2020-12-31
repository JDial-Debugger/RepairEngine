package ast.psi;

import ast.interfaces.BinaryExpressionDelegate;
import ast.interfaces.NodeDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;

import java.util.Map;

public class PsiBinaryExpressionDelegate extends PsiExpressionDelegate
		implements BinaryExpressionDelegate {

	protected PsiBinaryExpressionDelegate(NodeConfig<? extends PsiBinaryExpression> config) {
		super(config);
	}

	@Override
	public void accept(VisitorDelegate visitor) {
		visitor.visitBinaryExpression(this);
	}
}
