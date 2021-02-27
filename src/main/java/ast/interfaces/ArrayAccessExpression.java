package ast.interfaces;

public interface ArrayAccessExpression extends Expression {
	Expression getArray();
	Expression getIndex();
}
