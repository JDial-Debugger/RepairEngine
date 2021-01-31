package ast.psi;

import ast.interfaces.CodeBlock;
import ast.interfaces.Statement;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiStatement;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class CodeBlockImpl extends NodeImplBase implements CodeBlock {

	protected CodeBlockImpl(NodeConfig<? extends com.intellij.psi.PsiCodeBlock> config) {
		super(config);
	}

	@Override
	public void addStatement(Statement statement) {
		PsiStatement wrappedStatement = super.extractor.getDelegateElement(PsiStatement.class,
				statement);
		this.element.add(wrappedStatement);
	}

	@Override
	public void addStatements(Statement... statements) {
		for (Statement stmt : statements) {
			this.addStatement(stmt);
		}
	}

	@NotNull
	@Override
	public Iterator<Statement> iterator() {
		PsiCodeBlock myElement = (PsiCodeBlock) this.element;
		DelegateStore delegateStore = this.delegateStore;
		return new Iterator<Statement>() {
			int curIndex = 0;
			@Override
			public boolean hasNext() {
				return curIndex < myElement.getStatements().length;
			}

			@Override
			public Statement next() {
				PsiStatement delegate = myElement.getStatements()[curIndex++];
				return (Statement) delegateStore.getNodeFrom(delegate);
			}
		};
	}
}
