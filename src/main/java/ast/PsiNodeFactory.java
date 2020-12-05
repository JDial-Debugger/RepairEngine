package ast;

import com.intellij.psi.*;
import intellij.CommandProcessorDelegate;

import java.util.List;

public class PsiNodeFactory implements NodeFactory {

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

	private PsiElementFactory psiElementFactory;
	private CommandProcessorDelegate commandProcessor;
	private PsiElementExtractor elementExtractor;

	public PsiNodeFactory(
			PsiElementFactory psiElementFactory,
			CommandProcessorDelegate commandProcessor,
			PsiElementExtractor elementExtractor) {
		this.psiElementFactory = psiElementFactory;
		this.commandProcessor = commandProcessor;
		this.elementExtractor = elementExtractor;
	}

	@Override
	public StatementDelegate getReturnStatement(ExpressionDelegate expressionToReturn) {
		PsiStatement statementToWrap = this.psiElementFactory.createStatementFromText("return "
				+ expressionToReturn.toString()
				+ ";", null);
		return new PsiStatementDelegate(statementToWrap);
	}

	@Override
	public CodeBlockDelegate getCodeBlockFromStatements(List<StatementDelegate> statements) {
		PsiCodeBlock codeBlock = this.psiElementFactory.createCodeBlock();
		for (StatementDelegate statement : statements) {
			codeBlock.add(this.elementExtractor.getWrappedElement(PsiStatement.class, statement));
		}
		return new PsiCodeBlockDelegate(codeBlock);
	}

	@Override
	public CodeBlockDelegate getEmptyCodeBlock() {
		return new PsiCodeBlockDelegate(this.psiElementFactory.createCodeBlock());
	}

	@Override
	public ExpressionDelegate getExpressionFromText(String text) {
		PsiExpression result = this.psiElementFactory.createExpressionFromText(text, null);
		return new PsiExpressionDelegate(result);
	}

	@Override
	public StatementDelegate getStatementFromText(String text) {
		PsiStatement result = this.psiElementFactory.createStatementFromText(text, null);
		return new PsiStatementDelegate(result);
	}

	@Override
	public DeclarationStatementDelegate getEmptyDeclarationStatement() {
		String defaultText = "int a = 0;";
		PsiDeclarationStatement result
				= (PsiDeclarationStatement) psiElementFactory.createStatementFromText(defaultText,
				null);
		return new PsiDeclarationStatementDelegate(result);
	}

	@Override
	public DeclarationStatementDelegate getDeclarationStatement(
			String name, TypeDelegate type, ExpressionDelegate initializer) {
		PsiDeclarationStatement result = this.psiElementFactory.createVariableDeclarationStatement(
				name,
				this.elementExtractor.getWrappedType(type),
				this.elementExtractor.getWrappedElement(PsiExpression.class, initializer));
		return new PsiDeclarationStatementDelegate(result);
	}

	@Override
	public IfStatementDelegate getIfStatement(
			ExpressionDelegate condition, CodeBlockDelegate thenBody) {
		PsiIfStatement ifStatement = this.getIfStatement(this.elementExtractor.getWrappedElement(PsiExpression.class,
				condition),
				this.elementExtractor.getWrappedElement(PsiCodeBlock.class, thenBody));
		return new PsiIfStatementDelegate(ifStatement);
	}

	private PsiIfStatement getIfStatement(PsiExpression condition, PsiCodeBlock thenBody) {
		String defaultIfText = "if(true){}";
		PsiIfStatement ifStatement
				= (PsiIfStatement) this.psiElementFactory.createStatementFromText(defaultIfText,
				null);
		ifStatement.getCondition().replace(condition);
		ifStatement.getThenBranch().replace(thenBody);
		return ifStatement;
	}

	@Override
	public IfStatementDelegate getIfStatement(
			ExpressionDelegate condition, CodeBlockDelegate thenBody, CodeBlockDelegate elseBody) {
		PsiIfStatement result = this.getIfStatement(this.elementExtractor.getWrappedElement(PsiExpression.class,
				condition),
				this.elementExtractor.getWrappedElement(PsiCodeBlock.class, thenBody));
		result = this.addElseBodyToIfStatement(result,
				this.elementExtractor.getWrappedElement(PsiCodeBlock.class, elseBody));
		return new PsiIfStatementDelegate(result);
	}

	private PsiIfStatement addElseBodyToIfStatement(PsiIfStatement ifStmt, PsiCodeBlock elseBody) {
		ifStmt.getElseBranch().replace(elseBody);
		return ifStmt;
	}

	@Override
	public MethodDelegate getMethod(
			TypeDelegate returnType,
			String name,
			ParameterListDelegate paramList,
			CodeBlockDelegate body) {
		PsiMethod method = getEmptyWrappedMethod();
		method.setName(name);
		replaceChildren(method, returnType, paramList, body);
		return new PsiMethodDelegate(method);
	}

	private void replaceChildren(
			PsiMethod method,
			TypeDelegate returnType,
			ParameterListDelegate paramList,
			CodeBlockDelegate body) {
		this.replaceReturnType(method, returnType);
		this.replaceParamList(method, paramList);
		this.replaceBody(method, body);
	}

	private void replaceReturnType(PsiMethod method, TypeDelegate returnType) {
		PsiType wrappedReturnType = this.elementExtractor.getWrappedType(returnType);
		method.getReturnTypeElement().replace(this.psiElementFactory.createTypeElement(
				wrappedReturnType));
	}

	private void replaceParamList(PsiMethod method, ParameterListDelegate paramList) {
		if (paramList != null) {
			method.getParameterList().replace(this.elementExtractor.getWrappedElement(PsiParameterList.class,
					paramList));
		}
	}

	private void replaceBody(PsiMethod method, CodeBlockDelegate body) {
		method.getBody().replace(this.elementExtractor.getWrappedElement(PsiCodeBlock.class, body));
	}

	@Override
	public MethodDelegate getEmptyMethod() {
		PsiMethod result = this.getEmptyWrappedMethod();
		return new PsiMethodDelegate(result);
	}

	private PsiMethod getEmptyWrappedMethod() {
		String defaultText = "void f() {}";
		return psiElementFactory.createMethodFromText(defaultText, null);
	}
}
