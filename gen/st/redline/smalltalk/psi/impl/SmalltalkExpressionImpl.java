// This is a generated file. Not intended for manual editing.
package st.redline.smalltalk.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import st.redline.smalltalk.psi.*;

public class SmalltalkExpressionImpl extends SmalltalkPsiElementImpl implements SmalltalkExpression {

  public SmalltalkExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmalltalkVisitor) ((SmalltalkVisitor)visitor).visitExpression(this);
    else super.accept(visitor);
  }

}
