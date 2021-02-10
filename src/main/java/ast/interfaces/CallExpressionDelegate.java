package ast.interfaces;

import java.util.List;

public interface CallExpressionDelegate extends  ExpressionDelegate {

    List<ExpressionDelegate> getParameterList();

    String getMethodName();
}
