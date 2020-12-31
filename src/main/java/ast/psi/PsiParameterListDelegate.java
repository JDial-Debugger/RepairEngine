package ast.psi;

import ast.interfaces.NodeDelegate;
import ast.interfaces.ParameterListDelegate;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiParameterList;
import com.intellij.psi.PsiReturnStatement;

import java.util.Map;

public class PsiParameterListDelegate extends PsiNodeDelegateBase implements ParameterListDelegate {
	protected PsiParameterListDelegate(NodeConfig<? extends PsiParameterList> config) {
		super(config);
	}
}
