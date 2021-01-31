package ast.psi;

import ast.interfaces.*;
import com.intellij.psi.*;

import java.util.HashMap;

public class NodeFactoryImpl implements NodeFactory {

	public NodeFactoryImpl() {
	}

	@Override
	public AssertStatement getNode(com.intellij.psi.PsiAssertStatement delegate) {
		return new PsiAssertStatement(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public AssignExpression getNode(PsiAssignmentExpression delegate) {
		return new PsiAssignExpression(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public BinaryExpression getNode(com.intellij.psi.PsiBinaryExpression delegate) {
		return new PsiBinaryExpression(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public CodeBlock getNode(com.intellij.psi.PsiCodeBlock delegate) {
		return new PsiCodeBlock(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public DeclarationStatement getNode(com.intellij.psi.PsiDeclarationStatement delegate) {
		return new PsiDeclarationStatement(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public Expression getNode(com.intellij.psi.PsiExpression delegate) {
		return new PsiExpression(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public ExpressionStatement getNode(com.intellij.psi.PsiExpressionStatement delegate) {
		return new PsiExpressionStatement(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public File getNode(com.intellij.psi.PsiFile delegate) {
		return new PsiFile(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public IfStatement getNode(com.intellij.psi.PsiIfStatement delegate) {
		return new PsiIfStatement(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public LiteralExpression getNode(com.intellij.psi.PsiLiteralExpression delegate) {
		return new PsiLiteralExpression(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public Method getNode(com.intellij.psi.PsiMethod delegate) {
		return new PsiMethod(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public Node getNode(PsiElement delegate) {
		return new PsiNodeBase(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public ParameterList getNode(com.intellij.psi.PsiParameterList delegate) {
		return new PsiParameterList(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public ReturnStatement getNode(com.intellij.psi.PsiReturnStatement delegate) {
		return new PsiReturnStatement(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public Statement getNode(com.intellij.psi.PsiStatement delegate) {
		return new PsiStatement(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				new DelegateStoreImpl(new HashMap<>(), new NodeFactoryImpl())));
	}

	@Override
	public Type getType(com.intellij.psi.PsiType delegate) {
		return new PsiType(delegate);
	}
}
