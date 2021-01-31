package ast.psi;

import ast.interfaces.*;
import com.intellij.psi.*;

import java.util.HashMap;

public class NodeFactoryImpl implements NodeFactory {

	private DelegateStore delegateStore;

	public NodeFactoryImpl() {
		this.delegateStore = new DelegateStoreImpl(
				new HashMap<>(),
				new HashMap<>(),
				new NodeFactoryImpl());
	}

	@Override
	public AssertStatement getNode(com.intellij.psi.PsiAssertStatement delegate) {
		return new AssertStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public AssignExpression getNode(PsiAssignmentExpression delegate) {
		return new AssignExpressionImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public BinaryExpression getNode(com.intellij.psi.PsiBinaryExpression delegate) {
		return new BinaryExpressionImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public CodeBlock getNode(com.intellij.psi.PsiCodeBlock delegate) {
		return new CodeBlockImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public DeclarationStatement getNode(com.intellij.psi.PsiDeclarationStatement delegate) {
		return new DeclarationStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public Expression getNode(com.intellij.psi.PsiExpression delegate) {
		return new ExpressionImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public ExpressionStatement getNode(com.intellij.psi.PsiExpressionStatement delegate) {
		return new ExpressionStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public File getNode(com.intellij.psi.PsiFile delegate) {
		return new FileImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public IfStatement getNode(com.intellij.psi.PsiIfStatement delegate) {
		return new IfStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public LiteralExpression getNode(com.intellij.psi.PsiLiteralExpression delegate) {
		return new LiteralExpressionImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public Method getNode(com.intellij.psi.PsiMethod delegate) {
		return new MethodImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public Node getNode(PsiElement delegate) {
		return new NodeImplBase(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public ParameterList getNode(com.intellij.psi.PsiParameterList delegate) {
		return new ParameterListImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public ReturnStatement getNode(com.intellij.psi.PsiReturnStatement delegate) {
		return new ReturnStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public Statement getNode(com.intellij.psi.PsiStatement delegate) {
		return new StatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore));
	}

	@Override
	public Type getType(com.intellij.psi.PsiType delegate) {
		return new TypeImpl(delegate);
	}
}
