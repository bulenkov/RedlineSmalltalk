package st.redline.smalltalk.parser;

import com.intellij.psi.tree.IElementType;

/**
 * Created with IntelliJ IDEA.
 * User: maya
 * Date: 6/3/12
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RedlineElementTypes {

    final IElementType COMMENT = new RedlineElementType("COMMENT");
    final IElementType STRING = new RedlineElementType("STRING");
    final IElementType DIGITS = new RedlineElementType("DIGITS");
    final IElementType IDENTIFIER = new RedlineElementType("IDENTIFIER");
    final IElementType KEYWORD = new RedlineElementType("KEYWORD");
    final IElementType WHITE_SPACE = new RedlineElementType("WHITE_SPACE");
}
