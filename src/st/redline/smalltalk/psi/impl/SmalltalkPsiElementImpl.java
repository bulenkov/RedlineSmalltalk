package st.redline.smalltalk.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import st.redline.smalltalk.psi.SmalltalkPsiElement;

/**
 * Created with IntelliJ IDEA.
 * User: maya
 * Date: 6/18/12
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmalltalkPsiElementImpl extends ASTWrapperPsiElement implements SmalltalkPsiElement {
    public SmalltalkPsiElementImpl(@org.jetbrains.annotations.NotNull ASTNode node) {
        super(node);
    }
}
