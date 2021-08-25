package ast.psi;

import ast.interfaces.*;
import ast.psi.factory.ArrayStringBuilder;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.tree.java.PsiArrayAccessExpressionImpl;

import java.util.List;

public class PsiNodeBuilder implements NodeBuilder {

	private final PsiElementFactory psiElementFactory;
	private final PsiElementExtractor elementExtractor;
	private final ArrayStringBuilder arrayStringBuilder;
	private final NodeFactory nodeFactory;

	private static final String ZERO = "0";
	private static final String ZERO_DECIMAL = "0.0d";
	private static final String ZERO_FLOAT = "0.0f";
	private static final String ZERO_LONG = "0.0L";
	private static final String ZERO_CHAR = "\'\\u0000\'";
	private static final String FALSE = "false";

	public PsiNodeBuilder(
			PsiElementFactory psiElementFactory,
			PsiElementExtractor elementExtractor,
			ArrayStringBuilder arrayStringBuilder,
			NodeFactory nodeFactory) {
		this.psiElementFactory = psiElementFactory;
		this.elementExtractor = elementExtractor;
		this.arrayStringBuilder = arrayStringBuilder;
		this.nodeFactory = nodeFactory;
	}


	//  TODO: Implement this
	public DeclarationStatement getEmptyArrayDeclaration(int... dimensions) {
		return null;
	}

	@Override
	public LiteralExpression buildLiteralIntExpression(int expressionContents) {
		PsiLiteralExpression delegate
				= (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(""
				+ expressionContents, null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public DeclarationStatement buildEmptyArrayDeclaration(
			Type type, String name, Integer[] dimensions) {
		String defaultInitValue = this.buildDefaultLiteralExpressionFor(type).toString();
		String statementText = this.arrayStringBuilder.buildArrayDeclarationStatement(
				type,
				name,
				defaultInitValue,
				dimensions);
		PsiDeclarationStatement delegate
				= (PsiDeclarationStatement) this.psiElementFactory.createStatementFromText(
				statementText,
				null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public LiteralExpression buildDefaultLiteralExpressionFor(Type type) {
		PsiLiteralExpression delegate;
		PrimitiveType asEnum = type.asEnum();
		if (asEnum == PrimitiveType.BYTE) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(
					ZERO,
					null);
		} else if (asEnum == PrimitiveType.BOOLEAN) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(
					FALSE,
					null);
		} else if (asEnum == PrimitiveType.CHAR) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(
					ZERO_CHAR,
					null);
		} else if (asEnum == PrimitiveType.DOUBLE) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(
					ZERO_DECIMAL,
					null);
		} else if (asEnum == PrimitiveType.FLOAT) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(
					ZERO_FLOAT,
					null);
		} else if (asEnum == PrimitiveType.INT) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(
					ZERO,
					null);
		} else if (asEnum == PrimitiveType.LONG) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(
					ZERO_LONG,
					null);
		} else if (asEnum == PrimitiveType.SHORT) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(
					ZERO,
					null);
		} else {
			String enumAsString = asEnum == null ? null + "" : asEnum.toString();
			throw new InvalidTypeException("Provided type "
					+ enumAsString
					+ " does not have a default value");
		}
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public Statement buildReturnStatement(Expression expressionToReturn) {
		PsiStatement delegate = this.psiElementFactory.createStatementFromText("return "
				+ expressionToReturn.toString()
				+ ";", null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public CodeBlock buildCodeBlockFromStatements(List<Statement> statements) {
		PsiCodeBlock delegate = this.psiElementFactory.createCodeBlock();
		for (Statement statement : statements) {
			delegate.add(this.elementExtractor.getDelegateElement(PsiStatement.class, statement));
		}
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public CodeBlock buildEmptyCodeBlock() {
		PsiCodeBlock delegate = this.psiElementFactory.createCodeBlock();
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public Expression buildExpressionFromText(String text) {
		PsiExpression delegate = this.psiElementFactory.createExpressionFromText(text, null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public Statement buildStatementFromText(String text) {
		PsiStatement delegate = this.psiElementFactory.createStatementFromText(text, null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public DeclarationStatement buildEmptyDeclarationStatement() {
		String defaultText = "int a = 0;";
		PsiDeclarationStatement delegate
				= (PsiDeclarationStatement) psiElementFactory.createStatementFromText(
				defaultText,
				null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public DeclarationStatement buildDeclarationStatement(
			String name, Type type, Expression initializer) {

		PsiType typeDelegate = this.elementExtractor.getDelegateType(type);
		PsiExpression expressionDelegate
				= this.elementExtractor.getDelegateElement(PsiExpression.class, initializer);
		PsiDeclarationStatement delegate
				= this.psiElementFactory.createVariableDeclarationStatement(
				name,
				typeDelegate,
				expressionDelegate);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public IfStatement buildIfStatement(
			Expression condition, CodeBlock thenBody) {

		PsiExpression conditionDelegate
				= this.elementExtractor.getDelegateElement(PsiExpression.class, condition);
		PsiCodeBlock bodyDelegate = this.elementExtractor.getDelegateElement(
				PsiCodeBlock.class,
				thenBody);

		PsiIfStatement delegate = this.buildIfStatement(conditionDelegate, bodyDelegate);

		return this.nodeFactory.getNode(delegate);
	}

	private PsiIfStatement buildIfStatement(PsiExpression condition, PsiCodeBlock thenBody) {
		String defaultIfText = "if(true){}";
		PsiIfStatement ifStatement
				= (PsiIfStatement) this.psiElementFactory.createStatementFromText(
				defaultIfText,
				null);
		ifStatement.getCondition().replace(condition);
		ifStatement.getThenBranch().replace(thenBody);
		return ifStatement;
	}

	@Override
	public IfStatement buildIfStatement(
			Expression condition, CodeBlock thenBody, CodeBlock elseBody) {
		PsiIfStatement delegate = this.buildIfStatement(
				this.elementExtractor.getDelegateElement(
						PsiExpression.class,
						condition),
				this.elementExtractor.getDelegateElement(PsiCodeBlock.class, thenBody));
		delegate = this.addElseBodyToIfStatement(
				delegate,
				this.elementExtractor.getDelegateElement(PsiCodeBlock.class, elseBody));
		return this.nodeFactory.getNode(delegate);
	}

	private PsiIfStatement addElseBodyToIfStatement(
			PsiIfStatement ifStmt, PsiCodeBlock elseBody) {
		ifStmt.getElseBranch().replace(elseBody);
		return ifStmt;
	}

	@Override
	public Method buildMethod(
			Type returnType, String name, ParameterList paramList, CodeBlock body) {
		com.intellij.psi.PsiType returnTypeDelegate = this.elementExtractor.getDelegateType(
				returnType);
		PsiMethod delegate = this.psiElementFactory.createMethod(name, returnTypeDelegate);
		replaceChildren(delegate, paramList, body);
		return this.nodeFactory.getNode(delegate);
	}

	private void replaceChildren(
			PsiMethod method, ParameterList paramList, CodeBlock body) {
		this.replaceParamList(method, paramList);
		this.replaceBody(method, body);
	}

	private void replaceParamList(PsiMethod method, ParameterList paramList) {
		if (paramList != null) {
			method.getParameterList().replace(this.elementExtractor.getDelegateElement(
					PsiParameterList.class,
					paramList));
		}
	}

	private void replaceBody(PsiMethod method, CodeBlock body) {
		PsiCodeBlock originalBody = method.getBody();
		//  Factory result should never be null so no need to handle when it isn't
		if (originalBody != null) {
			originalBody.replace(this.elementExtractor.getDelegateElement(
					PsiCodeBlock.class,
					body));
		}
	}

	@Override
	public BinaryExpression buildBinaryExpression(
			Expression left, BinaryOperator op, Expression right) {
		PsiBinaryExpression delegate
				= (PsiBinaryExpression) this.psiElementFactory.createExpressionFromText(left.toString()
				+ op.toString()
				+ right.toString(), null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public Expression buildMethodCall(String methodName, Expression... params) {
		//  Psi factory API does not have parameter list creation so we must construct it from text
		StringBuilder callText = new StringBuilder();
		callText.append(methodName);
		callText.append("(");
		for (int i = 0; i < params.length; ++i) {
			callText.append(params[i].toString());
			if (i != params.length - 1) {
				callText.append(",");
			}
		}
		callText.append(")");
		PsiMethodCallExpression delegate
				= (PsiMethodCallExpression) this.psiElementFactory.createExpressionFromText(callText
				.toString(), null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public AssignExpression buildAssignExpression(Expression left, Expression right) {
		return this.buildAssignExpression(left, AssignOperator.SIMPLE, right);
	}

	@Override
	public AssignExpression buildAssignExpression(
			Expression left, AssignOperator op, Expression right) {
		String expressionText = left.toString() + op.toString() + right.toString();
		PsiAssignmentExpression delegate
				= (PsiAssignmentExpression) this.psiElementFactory.createExpressionFromText(
				expressionText,
				null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public ExpressionStatement buildExpressionStatement(Expression expression) {
		String expressionText = expression.toString() + ";";
		PsiExpressionStatement delegate
				= (PsiExpressionStatement) this.psiElementFactory.createStatementFromText(
				expressionText,
				null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public ForStatement buildForStatement(
			Statement init,
			Expression condition,
			Statement update,
			Statement body) {

		String initText = init == null ? " ;" : init.toString();
		String conditionText = condition == null ? "" : condition.toString();
		String updateText = "";
		if (update != null) {
			updateText = update.toString();
			boolean lastCharInUpdateTextIsSemiColon
					= updateText.charAt(updateText.length() - 1) == ';';
			if (lastCharInUpdateTextIsSemiColon) {
				updateText = updateText.substring(0, updateText.length() - 1);
			}
		}
		String bodyText = body == null ? ";" : body.toString();

		String statementText = "for ("
				+ initText
				+ " "
				+ conditionText
				+ "; "
				+ updateText
				+ ")"
				+ bodyText;
		PsiForStatement delegate = (PsiForStatement) this.psiElementFactory.createStatementFromText(
				statementText,
				null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public Type buildType(PrimitiveType primitiveType) {
		PsiType delegate = null;
		switch (primitiveType) {
			case BYTE:
				delegate = PsiType.BYTE;
				break;
			case BOOLEAN:
				delegate = PsiType.BOOLEAN;
				break;
			case CHAR:
				delegate = PsiType.CHAR;
				break;
			case DOUBLE:
				delegate = PsiType.DOUBLE;
				break;
			case FLOAT:
				delegate = PsiType.FLOAT;
				break;
			case INT:
				delegate = PsiType.INT;
				break;
			case LONG:
				delegate = PsiType.LONG;
				break;
			case SHORT:
				delegate = PsiType.SHORT;
				break;
			case NULL:
				delegate = PsiType.NULL;
				break;
			case VOID:
				delegate = PsiType.VOID;
				break;
			default:
				break;
		}
		return this.nodeFactory.getType(delegate);
	}

	@Override
	public UnaryExpression buildUnaryExpression(Expression expression, UnaryOperator op) {
		PsiUnaryExpression delegate;
		if (op == UnaryOperator.POST_DECREMENT || op == UnaryOperator.POST_INCREMENT) {
			delegate
					= (PsiUnaryExpression) this.psiElementFactory.createExpressionFromText(
					"("
							+ expression
							+ ")" + op.toString(), null);
		} else {
			delegate
					= (PsiUnaryExpression) this.psiElementFactory.createExpressionFromText(op.toString()
					+ "("
					+ expression
					+ ")", null);
		}
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public ArrayAccessExpression buildArrayAccessExpression(Expression array, Expression index) {
		String exprText = array.toString() + "[" + index + "]";
		PsiArrayAccessExpression delegate
				= (PsiArrayAccessExpression) this.psiElementFactory.createExpressionFromText(
				exprText,
				null);
		return this.nodeFactory.getNode(delegate);
	}
}
