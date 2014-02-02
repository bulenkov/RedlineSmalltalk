package st.redline.smalltalk.psi;

import com.intellij.psi.tree.IElementType;
import st.redline.smalltalk.SmalltalkLanguage;

/**
 * Created with IntelliJ IDEA.
 * User: maya
 * Date: 6/19/12
 * Time: 7:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class SmalltalkTokenType extends IElementType {
    public SmalltalkTokenType(String debug) {
        super(debug, SmalltalkLanguage.INSTANCE);
    }
}
