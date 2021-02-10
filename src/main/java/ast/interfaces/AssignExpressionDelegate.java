package ast.interfaces;

public interface AssignExpressionDelegate extends ExpressionDelegate {

    ExpressionDelegate getLExpression();

    ExpressionDelegate getRExpression();
}
