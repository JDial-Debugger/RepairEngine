package ast.psi;

import ast.interfaces.*;
import ast.psi.factory.ArrayStringBuilder;
import com.intellij.psi.*;
import intellij.CommandProcessorDelegate;

import java.util.List;

public class PsiNodeBuilder implements NodeBuilder {

	private static final PsiType BYTE = new PsiType(com.intellij.psi.PsiType.BYTE);
	private static final PsiType CHAR = new PsiType(com.intellij.psi.PsiType.CHAR);
	private static final PsiType DOUBLE = new PsiType(com.intellij.psi.PsiType.DOUBLE);
	private static final PsiType FLOAT = new PsiType(com.intellij.psi.PsiType.FLOAT);
	private static final PsiType INT = new PsiType(com.intellij.psi.PsiType.INT);
	private static final PsiType LONG = new PsiType(com.intellij.psi.PsiType.LONG);
	private static final PsiType SHORT = new PsiType(com.intellij.psi.PsiType.SHORT);
	private static final PsiType BOOLEAN = new PsiType(com.intellij.psi.PsiType.BOOLEAN);
	private static final PsiType VOID = new PsiType(com.intellij.psi.PsiType.VOID);
	private static final PsiType NULL = new PsiType(com.intellij.psi.PsiType.NULL);

	private final PsiElementFactory psiElementFactory;
	//  TODO: all methods that modify an existing tree MUST do so via this interface
	private final CommandProcessorDelegate commandProcessor;
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
			CommandProcessorDelegate commandProcessor,
			PsiElementExtractor elementExtractor,
			ArrayStringBuilder arrayStringBuilder,
			NodeFactory nodeFactory) {
		this.psiElementFactory = psiElementFactory;
		this.commandProcessor = commandProcessor;
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
		LiteralExpressionImpl result
				= (LiteralExpressionImpl) this.psiElementFactory.createExpressionFromText(""
				+ expressionContents, null);
		return this.nodeFactory.getNode(result);
	}

	@Override
	public DeclarationStatement buildEmptyArrayDeclaration(
			Type type, String name, Integer[] dimensions) {
		String defaultInitValue = this.buildDefaultLiteralExpressionFor(type).toString();
		String statementText = this.arrayStringBuilder.buildArrayDeclarationStatement(type,
				name,
				defaultInitValue,
				dimensions);
		DeclarationStatementImpl statement
				= (DeclarationStatementImpl) this.psiElementFactory.createStatementFromText(statementText,
				null);
		return this.nodeFactory.getNode(statement);
	}

	@Override
	public LiteralExpression buildDefaultLiteralExpressionFor(Type type) {
		LiteralExpressionImpl delegate;
		PrimitiveType asEnum = type.asEnum();
		if (asEnum == PrimitiveType.BYTE) {
			delegate = (LiteralExpressionImpl) this.psiElementFactory.createExpressionFromText(ZERO,
					null);
		} else if (asEnum == PrimitiveType.BOOLEAN) {
			delegate = (LiteralExpressionImpl) this.psiElementFactory.createExpressionFromText(FALSE,
					null);
		} else if (asEnum == PrimitiveType.CHAR) {
			delegate = (LiteralExpressionImpl) this.psiElementFactory.createExpressionFromText(ZERO_CHAR,
					null);
		} else if (asEnum == PrimitiveType.DOUBLE) {
			delegate = (LiteralExpressionImpl) this.psiElementFactory.createExpressionFromText(ZERO_DECIMAL,
					null);
		} else if (asEnum == PrimitiveType.FLOAT) {
			delegate = (LiteralExpressionImpl) this.psiElementFactory.createExpressionFromText(ZERO_FLOAT,
					null);
		} else if (asEnum == PrimitiveType.INT) {
			delegate = (LiteralExpressionImpl) this.psiElementFactory.createExpressionFromText(ZERO,
					null);
		} else if (asEnum == PrimitiveType.LONG) {
			delegate = (LiteralExpressionImpl) this.psiElementFactory.createExpressionFromText(ZERO_LONG,
					null);
		} else if (asEnum == PrimitiveType.SHORT) {
			delegate = (LiteralExpressionImpl) this.psiElementFactory.createExpressionFromText(ZERO,
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
		StatementImpl delegate = this.psiElementFactory.createStatementFromText("return "
				+ expressionToReturn.toString()
				+ ";", null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public CodeBlock buildCodeBlockFromStatements(List<Statement> statements) {
		CodeBlockImpl delegate = this.psiElementFactory.createCodeBlock();
		for (Statement statement : statements) {
			delegate.add(this.elementExtractor.getDelegateElement(StatementImpl.class, statement));
		}
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public CodeBlock buildEmptyCodeBlock() {
		CodeBlockImpl delegate = this.psiElementFactory.createCodeBlock();
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public Expression buildExpressionFromText(String text) {
		ExpressionImpl delegate = this.psiElementFactory.createExpressionFromText(text, null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public Statement buildStatementFromText(String text) {
		StatementImpl delegate = this.psiElementFactory.createStatementFromText(text, null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public DeclarationStatement buildEmptyDeclarationStatement() {
		String defaultText = "int a = 0;";
		DeclarationStatementImpl delegate
				= (DeclarationStatementImpl) psiElementFactory.createStatementFromText(defaultText,
				null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public DeclarationStatement buildDeclarationStatement(
			String name, Type type, Expression initializer) {

		com.intellij.psi.PsiType typeDelegate = this.elementExtractor.getDelegateType(type);
		ExpressionImpl expressionDelegate
				= this.elementExtractor.getDelegateElement(ExpressionImpl.class, initializer);
		DeclarationStatementImpl delegate
				= this.psiElementFactory.createVariableDeclarationStatement(name,
				typeDelegate,
				expressionDelegate);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public IfStatement buildIfStatement(
			Expression condition, CodeBlock thenBody) {

		ExpressionImpl conditionDelegate
				= this.elementExtractor.getDelegateElement(ExpressionImpl.class, condition);
		CodeBlockImpl bodyDelegate = this.elementExtractor.getDelegateElement(
				CodeBlockImpl.class,
				thenBody);

		IfStatementImpl delegate = this.buildIfStatement(conditionDelegate, bodyDelegate);

		return this.nodeFactory.getNode(delegate);
	}

	private IfStatementImpl buildIfStatement(ExpressionImpl condition, CodeBlockImpl thenBody) {
		String defaultIfText = "if(true){}";
		IfStatementImpl ifStatement
				= (IfStatementImpl) this.psiElementFactory.createStatementFromText(defaultIfText,
				null);
		ifStatement.getCondition().replace(condition);
		ifStatement.getThenBranch().replace(thenBody);
		return ifStatement;
	}

	@Override
	public IfStatement buildIfStatement(
			Expression condition, CodeBlock thenBody, CodeBlock elseBody) {
		IfStatementImpl delegate = this.buildIfStatement(this.elementExtractor.getDelegateElement(
				ExpressionImpl.class,
				condition),
				this.elementExtractor.getDelegateElement(CodeBlockImpl.class, thenBody));
		delegate = this.addElseBodyToIfStatement(delegate,
				this.elementExtractor.getDelegateElement(CodeBlockImpl.class, elseBody));
		return this.nodeFactory.getNode(delegate);
	}

	private IfStatementImpl addElseBodyToIfStatement(IfStatementImpl ifStmt, CodeBlockImpl elseBody) {
		ifStmt.getElseBranch().replace(elseBody);
		return ifStmt;
	}

	@Override
	public Method buildMethod(
			Type returnType,
			String name,
			ParameterList paramList,
			CodeBlock body) {
		com.intellij.psi.PsiType
				returnTypeDelegate = this.elementExtractor.getDelegateType(returnType);
		MethodImpl delegate = this.psiElementFactory.createMethod(name, returnTypeDelegate);
		replaceChildren(delegate, paramList, body);
		return this.nodeFactory.getNode(delegate);
	}

	private void replaceChildren(
			MethodImpl method, ParameterList paramList, CodeBlock body) {
		this.replaceParamList(method, paramList);
		this.replaceBody(method, body);
	}

	private void replaceParamList(MethodImpl method, ParameterList paramList) {
		if (paramList != null) {
			method.getParameterList().replace(this.elementExtractor.getDelegateElement(
					ParameterListImpl.class,
					paramList));
		}
	}

	private void replaceBody(MethodImpl method, CodeBlock body) {
		CodeBlockImpl originalBody = method.getBody();
		//  Factory result should never be null so no need to handle when it isn't
		if (originalBody != null) {
			originalBody.replace(this.elementExtractor.getDelegateElement(
					CodeBlockImpl.class,
					body));
		}
	}

	@Override
	public BinaryExpression buildBinaryExpression(
			Expression left, BinaryOperator op, Expression right) {
		BinaryExpressionImpl delegate
				= (BinaryExpressionImpl) this.psiElementFactory.createExpressionFromText(left.toString()
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
}
