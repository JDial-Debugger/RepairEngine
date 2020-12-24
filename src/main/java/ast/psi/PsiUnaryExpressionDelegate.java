package ast.psi;

import ast.interfaces.UnaryExpressionDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiUnaryExpression;

public class PsiUnaryExpressionDelegate extends PsiExpressionDelegate implements UnaryExpressionDelegate {

    protected PsiUnaryExpressionDelegate(PsiUnaryExpression expression) {
        super(expression);
    }

    @Override
    public void accept(VisitorDelegate visitor) {
        visitor.visitUnaryExpression(this);
    }
}
