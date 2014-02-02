// This is a generated file. Not intended for manual editing.
package st.redline.smalltalk.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import st.redline.smalltalk.psi.impl.*;

public interface SmalltalkTypes {

  IElementType ST_ADD_EXPRESSION = new SmalltalkPsiElementType("ST_ADD_EXPRESSION");
  IElementType ST_EXPRESSION = new SmalltalkPsiElementType("ST_EXPRESSION");
  IElementType ST_IDENTIFIER = new SmalltalkPsiElementType("ST_IDENTIFIER");
  IElementType ST_LITERAL_EXPRESSION = new SmalltalkPsiElementType("ST_LITERAL_EXPRESSION");
  IElementType ST_MUL_EXPRESSION = new SmalltalkPsiElementType("ST_MUL_EXPRESSION");
  IElementType ST_REFERENCE_EXPRESSION = new SmalltalkPsiElementType("ST_REFERENCE_EXPRESSION");

  IElementType ST_ADD = new SmalltalkTokenType("+");
  IElementType ST_DOT = new SmalltalkTokenType(".");
  IElementType ST_ID = new SmalltalkTokenType("id");
  IElementType ST_MUL = new SmalltalkTokenType("*");
  IElementType ST_NUMBER = new SmalltalkTokenType("number");
  IElementType ST_STRING = new SmalltalkTokenType("string");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ST_ADD_EXPRESSION) {
        return new SmalltalkAddExpressionImpl(node);
      }
      else if (type == ST_EXPRESSION) {
        return new SmalltalkExpressionImpl(node);
      }
      else if (type == ST_IDENTIFIER) {
        return new SmalltalkIdentifierImpl(node);
      }
      else if (type == ST_LITERAL_EXPRESSION) {
        return new SmalltalkLiteralExpressionImpl(node);
      }
      else if (type == ST_MUL_EXPRESSION) {
        return new SmalltalkMulExpressionImpl(node);
      }
      else if (type == ST_REFERENCE_EXPRESSION) {
        return new SmalltalkReferenceExpressionImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
