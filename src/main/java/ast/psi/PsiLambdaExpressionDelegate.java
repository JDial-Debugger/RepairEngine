package ast.psi;

import ast.interfaces.LambdaExpressionDelegate;
import ast.interfaces.VisitorDelegate;
import  com.intellij.psi.PsiLambdaExpression;

public class PsiLambdaExpressionDelegate extends  PsiExpressionDelegate implements LambdaExpressionDelegate {

    protected PsiLambdaExpressionDelegate(PsiLambdaExpression expression) {
        super(expression);
    }

    @Override
    public void accept(VisitorDelegate visitor) {
        visitor.visitLambdaExpression(this);
    }
}
