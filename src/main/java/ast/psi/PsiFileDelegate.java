package ast.psi;

import ast.interfaces.FileDelegate;
import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiStatement;

import java.util.Map;

public class PsiFileDelegate extends PsiNodeDelegateBase implements FileDelegate {
	protected PsiFileDelegate(NodeConfig<? extends PsiFile> config) {
		super(config);
	}
}
