package ast.psi;

import ast.interfaces.*;
import com.intellij.psi.*;

import java.util.HashMap;

public class NodeFactoryImpl implements NodeFactory {

	public NodeFactoryImpl() {
	}

	@Override
	public AssertStatementDelegate getNode(PsiAssertStatement delegate) {
		return new PsiAssertStatementDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public AssignExpressionDelegate getNode(PsiAssignmentExpression delegate) {
		return new PsiAssignExpressionDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public BinaryExpressionDelegate getNode(PsiBinaryExpression delegate) {
		return new PsiBinaryExpressionDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public CodeBlockDelegate getNode(PsiCodeBlock delegate) {
		return new PsiCodeBlockDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public DeclarationStatementDelegate getNode(PsiDeclarationStatement delegate) {
		return new PsiDeclarationStatementDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public ExpressionDelegate getNode(PsiExpression delegate) {
		return new PsiExpressionDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public ExpressionStatementDelegate getNode(PsiExpressionStatement delegate) {
		return new PsiExpressionStatementDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public FileDelegate getNode(PsiFile delegate) {
		return new PsiFileDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public IfStatementDelegate getNode(PsiIfStatement delegate) {
		return new PsiIfStatementDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public LiteralExpressionDelegate getNode(PsiLiteralExpression delegate) {
		return new PsiLiteralExpressionDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public MethodDelegate getNode(PsiMethod delegate) {
		return new PsiMethodDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public NodeDelegate getNode(PsiElement delegate) {
		return new PsiNodeDelegateBase(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public ParameterListDelegate getNode(PsiParameterList delegate) {
		return new PsiParameterListDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public ReturnStatementDelegate getNode(PsiReturnStatement delegate) {
		return new PsiReturnStatementDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public StatementDelegate getNode(PsiStatement delegate) {
		return new PsiStatementDelegate(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public TypeDelegate getType(PsiType delegate) {
		return new PsiTypeDelegate(delegate);
	}
}
