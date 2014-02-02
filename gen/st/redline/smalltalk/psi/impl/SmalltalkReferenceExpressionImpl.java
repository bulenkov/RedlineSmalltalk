// This is a generated file. Not intended for manual editing.
package st.redline.smalltalk.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import st.redline.smalltalk.psi.*;

public class SmalltalkReferenceExpressionImpl extends SmalltalkExpressionImpl implements SmalltalkReferenceExpression {

  public SmalltalkReferenceExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public SmalltalkIdentifier getIdentifier() {
    return findNotNullChildByClass(SmalltalkIdentifier.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmalltalkVisitor) ((SmalltalkVisitor)visitor).visitReferenceExpression(this);
    else super.accept(visitor);
  }

}
