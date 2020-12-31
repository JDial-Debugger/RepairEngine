package ast.psi;

import ast.interfaces.*;
import ast.psi.factory.ArrayStringBuilder;
import com.intellij.psi.*;
import intellij.CommandProcessorDelegate;

import java.util.List;

public class PsiNodeBuilder implements NodeBuilder {

	private static final PsiTypeDelegate BYTE = new PsiTypeDelegate(PsiType.BYTE);
	private static final PsiTypeDelegate CHAR = new PsiTypeDelegate(PsiType.CHAR);
	private static final PsiTypeDelegate DOUBLE = new PsiTypeDelegate(PsiType.DOUBLE);
	private static final PsiTypeDelegate FLOAT = new PsiTypeDelegate(PsiType.FLOAT);
	private static final PsiTypeDelegate INT = new PsiTypeDelegate(PsiType.INT);
	private static final PsiTypeDelegate LONG = new PsiTypeDelegate(PsiType.LONG);
	private static final PsiTypeDelegate SHORT = new PsiTypeDelegate(PsiType.SHORT);
	private static final PsiTypeDelegate BOOLEAN = new PsiTypeDelegate(PsiType.BOOLEAN);
	private static final PsiTypeDelegate VOID = new PsiTypeDelegate(PsiType.VOID);
	private static final PsiTypeDelegate NULL = new PsiTypeDelegate(PsiType.NULL);

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
	public DeclarationStatementDelegate getEmptyArrayDeclaration(int... dimensions) {
		return null;
	}

	@Override
	public LiteralExpressionDelegate buildLiteralIntExpression(int expressionContents) {
		PsiLiteralExpression result
				= (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(""
				+ expressionContents, null);
		return this.nodeFactory.getNode(result);
	}

	@Override
	public DeclarationStatementDelegate buildEmptyArrayDeclaration(
			TypeDelegate type, String name, Integer[] dimensions) {
		String defaultInitValue = this.buildDefaultLiteralExpressionFor(type).toString();
		String statementText = this.arrayStringBuilder.buildArrayDeclarationStatement(type,
				name,
				defaultInitValue,
				dimensions);
		PsiDeclarationStatement statement
				= (PsiDeclarationStatement) this.psiElementFactory.createStatementFromText(statementText,
				null);
		return this.nodeFactory.getNode(statement);
	}

	@Override
	public LiteralExpressionDelegate buildDefaultLiteralExpressionFor(TypeDelegate type) {
		PsiLiteralExpression delegate;
		Type asEnum = type.asEnum();
		if (asEnum == Type.BYTE) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(ZERO,
					null);
		} else if (asEnum == Type.BOOLEAN) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(FALSE,
					null);
		} else if (asEnum == Type.CHAR) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(ZERO_CHAR,
					null);
		} else if (asEnum == Type.DOUBLE) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(ZERO_DECIMAL,
					null);
		} else if (asEnum == Type.FLOAT) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(ZERO_FLOAT,
					null);
		} else if (asEnum == Type.INT) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(ZERO,
					null);
		} else if (asEnum == Type.LONG) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(ZERO_LONG,
					null);
		} else if (asEnum == Type.SHORT) {
			delegate = (PsiLiteralExpression) this.psiElementFactory.createExpressionFromText(ZERO,
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
	public StatementDelegate buildReturnStatement(ExpressionDelegate expressionToReturn) {
		PsiStatement delegate = this.psiElementFactory.createStatementFromText("return "
				+ expressionToReturn.toString()
				+ ";", null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public CodeBlockDelegate buildCodeBlockFromStatements(List<StatementDelegate> statements) {
		PsiCodeBlock delegate = this.psiElementFactory.createCodeBlock();
		for (StatementDelegate statement : statements) {
			delegate.add(this.elementExtractor.getDelegateElement(PsiStatement.class, statement));
		}
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public CodeBlockDelegate buildEmptyCodeBlock() {
		PsiCodeBlock delegate = this.psiElementFactory.createCodeBlock();
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public ExpressionDelegate buildExpressionFromText(String text) {
		PsiExpression delegate = this.psiElementFactory.createExpressionFromText(text, null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public StatementDelegate buildStatementFromText(String text) {
		PsiStatement delegate = this.psiElementFactory.createStatementFromText(text, null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public DeclarationStatementDelegate buildEmptyDeclarationStatement() {
		String defaultText = "int a = 0;";
		PsiDeclarationStatement delegate
				= (PsiDeclarationStatement) psiElementFactory.createStatementFromText(defaultText,
				null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public DeclarationStatementDelegate buildDeclarationStatement(
			String name, TypeDelegate type, ExpressionDelegate initializer) {

		PsiType typeDelegate = this.elementExtractor.getDelegateType(type);
		PsiExpression expressionDelegate
				= this.elementExtractor.getDelegateElement(PsiExpression.class, initializer);
		PsiDeclarationStatement delegate
				= this.psiElementFactory.createVariableDeclarationStatement(name,
				typeDelegate,
				expressionDelegate);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public IfStatementDelegate buildIfStatement(
			ExpressionDelegate condition, CodeBlockDelegate thenBody) {

		PsiExpression conditionDelegate
				= this.elementExtractor.getDelegateElement(PsiExpression.class, condition);
		PsiCodeBlock bodyDelegate = this.elementExtractor.getDelegateElement(PsiCodeBlock.class,
				thenBody);

		PsiIfStatement delegate = this.buildIfStatement(conditionDelegate, bodyDelegate);

		return this.nodeFactory.getNode(delegate);
	}

	private PsiIfStatement buildIfStatement(PsiExpression condition, PsiCodeBlock thenBody) {
		String defaultIfText = "if(true){}";
		PsiIfStatement ifStatement
				= (PsiIfStatement) this.psiElementFactory.createStatementFromText(defaultIfText,
				null);
		ifStatement.getCondition().replace(condition);
		ifStatement.getThenBranch().replace(thenBody);
		return ifStatement;
	}

	@Override
	public IfStatementDelegate buildIfStatement(
			ExpressionDelegate condition, CodeBlockDelegate thenBody, CodeBlockDelegate elseBody) {
		PsiIfStatement delegate = this.buildIfStatement(this.elementExtractor.getDelegateElement(PsiExpression.class,
				condition),
				this.elementExtractor.getDelegateElement(PsiCodeBlock.class, thenBody));
		delegate = this.addElseBodyToIfStatement(delegate,
				this.elementExtractor.getDelegateElement(PsiCodeBlock.class, elseBody));
		return this.nodeFactory.getNode(delegate);
	}

	private PsiIfStatement addElseBodyToIfStatement(PsiIfStatement ifStmt, PsiCodeBlock elseBody) {
		ifStmt.getElseBranch().replace(elseBody);
		return ifStmt;
	}

	@Override
	public MethodDelegate buildMethod(
			TypeDelegate returnType,
			String name,
			ParameterListDelegate paramList,
			CodeBlockDelegate body) {
		PsiType returnTypeDelegate = this.elementExtractor.getDelegateType(returnType);
		PsiMethod delegate = this.psiElementFactory.createMethod(name, returnTypeDelegate);
		replaceChildren(delegate, paramList, body);
		return this.nodeFactory.getNode(delegate);
	}

	private void replaceChildren(
			PsiMethod method, ParameterListDelegate paramList, CodeBlockDelegate body) {
		this.replaceParamList(method, paramList);
		this.replaceBody(method, body);
	}

	private void replaceParamList(PsiMethod method, ParameterListDelegate paramList) {
		if (paramList != null) {
			method.getParameterList().replace(this.elementExtractor.getDelegateElement(PsiParameterList.class,
					paramList));
		}
	}

	private void replaceBody(PsiMethod method, CodeBlockDelegate body) {
		PsiCodeBlock originalBody = method.getBody();
		//  Factory result should never be null so no need to handle when it isn't
		if (originalBody != null) {
			originalBody.replace(this.elementExtractor.getDelegateElement(PsiCodeBlock.class,
					body));
		}
	}

	@Override
	public BinaryExpressionDelegate buildBinaryExpression(
			ExpressionDelegate left, BinaryOperator op, ExpressionDelegate right) {
		PsiBinaryExpression delegate
				= (PsiBinaryExpression) this.psiElementFactory.createExpressionFromText(left.toString()
				+ op.toString()
				+ right.toString(), null);
		return this.nodeFactory.getNode(delegate);
	}

	@Override
	public ExpressionDelegate buildMethodCall(String methodName, ExpressionDelegate... params) {
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
