// This is a generated file. Not intended for manual editing.
package st.redline.smalltalk.psi.impl;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

import static st.redline.smalltalk.psi.SmalltalkTypes.*;
import st.redline.smalltalk.psi.*;

public class SmalltalkLiteralExpressionImpl extends SmalltalkExpressionImpl implements SmalltalkLiteralExpression {

  public SmalltalkLiteralExpressionImpl(ASTNode node) {
    super(node);
  }

  @Override
  @Nullable
  public PsiElement getNumber() {
    return findChildByType(ST_NUMBER);
  }

  @Override
  @Nullable
  public PsiElement getString() {
    return findChildByType(ST_STRING);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SmalltalkVisitor) ((SmalltalkVisitor)visitor).visitLiteralExpression(this);
    else super.accept(visitor);
  }

}
