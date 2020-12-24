package ast.psi;

import ast.interfaces.MethodDelegate;
import ast.interfaces.NodeDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiStatement;

import java.util.Map;

public class PsiMethodDelegate extends PsiNodeDelegateBase implements MethodDelegate {

	protected PsiMethodDelegate(NodeConfig<? extends  PsiMethod> config) {
		super(config);
	}
}
