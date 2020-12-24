package ast.psi;

import ast.interfaces.CallExpressionDelegate;
import ast.interfaces.ExpressionDelegate;
import ast.interfaces.VisitorDelegate;
import com.intellij.psi.PsiCall;
import com.intellij.psi.PsiCallExpression;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiExpressionList;

import java.util.ArrayList;
import java.util.List;

public class PsiCallExpressionDelegate extends PsiExpressionDelegate implements CallExpressionDelegate {

    protected PsiCallExpressionDelegate(PsiCallExpression expression) {
        super((NodeConfig<? extends PsiExpression>) expression);
    }

    @Override
    public void accept(VisitorDelegate visitor) {
        visitor.visitCallExpression(this);
    }

    @Override
    public List<ExpressionDelegate> getParameterList() {
        PsiCall callExpr = (PsiCall) this.element; // downcast to PsiCall object
        PsiExpressionList expressionListObj = callExpr.getArgumentList();
        PsiExpression[] expressionList = expressionListObj.getExpressions();
        List<ExpressionDelegate> paramList = new ArrayList<>();
        for (PsiExpression expression: expressionList) {
            // put each expression into the delegate store
            paramList.add( (ExpressionDelegate) this.delegateStore.getNodeFrom(expression));
        }
        return paramList;
    }

    @Override
    public String getMethodName() {
        PsiCall callExpr = (PsiCall) this.element;
        return callExpr.getText();
    }
}
