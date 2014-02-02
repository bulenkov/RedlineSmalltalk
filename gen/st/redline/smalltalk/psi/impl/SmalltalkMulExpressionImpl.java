// This is a generated file. Not intended for manual editing.
package st.redline.smalltalk.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import st.redline.smalltalk.psi.*;

public class SmalltalkMulExpressionImpl extends SmalltalkExpressionImpl implements SmalltalkMulExpression {

  public SmalltalkMulExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  @NotNull
  public List<SmalltalkExpression> getExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, SmalltalkExpression.class);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmalltalkVisitor) ((SmalltalkVisitor)visitor).visitMulExpression(this);
    else super.accept(visitor);
  }

}
