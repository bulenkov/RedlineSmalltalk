// This is a generated file. Not intended for manual editing.
package st.redline.smalltalk.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static st.redline.smalltalk.psi.SmalltalkTypes.*;
import st.redline.smalltalk.psi.*;

public class SmalltalkIdentifierImpl extends SmalltalkPsiElementImpl implements SmalltalkIdentifier {

  public SmalltalkIdentifierImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ST_ID);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmalltalkVisitor) ((SmalltalkVisitor)visitor).visitIdentifier(this);
    else super.accept(visitor);
  }

}
