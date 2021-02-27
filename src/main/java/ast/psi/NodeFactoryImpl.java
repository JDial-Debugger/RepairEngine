package ast.psi;

import ast.interfaces.*;
import ast.psi.factory.ArrayStringBuilderImpl;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.PsiElementFactoryImpl;

import java.util.HashMap;

public class NodeFactoryImpl implements NodeFactory {

	private DelegateStore delegateStore;
	private NodeBuilder nodeBuilder;

	public NodeFactoryImpl(Project project) {
		this.delegateStore = new DelegateStoreImpl(new HashMap<>(), new HashMap<>(), this);
		this.nodeBuilder = new PsiNodeBuilder(new PsiElementFactoryImpl(project),
				new PsiElementExtractorImpl(),
				new ArrayStringBuilderImpl(),
				this);
	}

	@Override
	public AssertStatement getNode(com.intellij.psi.PsiAssertStatement delegate) {
		return new AssertStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public AssignExpression getNode(PsiAssignmentExpression delegate) {
		return new AssignExpressionImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public BinaryExpression getNode(com.intellij.psi.PsiBinaryExpression delegate) {
		return new BinaryExpressionImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public CodeBlock getNode(com.intellij.psi.PsiCodeBlock delegate) {
		return new CodeBlockImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public DeclarationStatement getNode(com.intellij.psi.PsiDeclarationStatement delegate) {
		return new DeclarationStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public Expression getNode(com.intellij.psi.PsiExpression delegate) {
		return new ExpressionImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public ExpressionStatement getNode(com.intellij.psi.PsiExpressionStatement delegate) {
		return new ExpressionStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public File getNode(com.intellij.psi.PsiFile delegate) {
		return new FileImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public IfStatement getNode(com.intellij.psi.PsiIfStatement delegate) {
		return new IfStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public LiteralExpression getNode(com.intellij.psi.PsiLiteralExpression delegate) {
		return new LiteralExpressionImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public LocalVariable getNode(PsiLocalVariable delegate) {
		return new LocalVariableImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public Method getNode(com.intellij.psi.PsiMethod delegate) {
		return new MethodImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public Node getNode(PsiElement delegate) {
		return new NodeImplBase(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public Parameter getNode(PsiParameter delegate) {
		return new ParameterImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public ParameterList getNode(com.intellij.psi.PsiParameterList delegate) {
		return new ParameterListImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public ReturnStatement getNode(com.intellij.psi.PsiReturnStatement delegate) {
		return new ReturnStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public Statement getNode(com.intellij.psi.PsiStatement delegate) {
		return new StatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public BlockStatement getNode(PsiBlockStatement delegate) {
		return new BlockStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public ForStatement getNode(PsiForStatement delegate) {
		return new ForStatementImpl(new NodeConfig<>(delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder));
	}

	@Override
	public UnaryExpression getNode(PsiUnaryExpression delegate) {
		return new UnaryExpressionImpl(new NodeConfig<>(
				delegate,
				new PsiElementExtractorImpl(),
				this.delegateStore,
				this.nodeBuilder)) {
		};
	}

	@Override
	public Type getType(com.intellij.psi.PsiType delegate) {
		return new TypeImpl(delegate);
	}
}
