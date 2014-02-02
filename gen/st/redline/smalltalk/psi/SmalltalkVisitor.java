// This is a generated file. Not intended for manual editing.
package st.redline.smalltalk.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;

public class SmalltalkVisitor extends PsiElementVisitor {

  public void visitAddExpression(@NotNull SmalltalkAddExpression o) {
    visitExpression(o);
  }

  public void visitExpression(@NotNull SmalltalkExpression o) {
    visitPsiElement(o);
  }

  public void visitIdentifier(@NotNull SmalltalkIdentifier o) {
    visitPsiElement(o);
  }

  public void visitLiteralExpression(@NotNull SmalltalkLiteralExpression o) {
    visitExpression(o);
  }

  public void visitMulExpression(@NotNull SmalltalkMulExpression o) {
    visitExpression(o);
  }

  public void visitReferenceExpression(@NotNull SmalltalkReferenceExpression o) {
    visitExpression(o);
  }

  public void visitPsiElement(@NotNull SmalltalkPsiElement o) {
    visitElement(o);
  }

}
