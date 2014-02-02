package st.redline.smalltalk.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: maya
 * Date: 6/3/12
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class RedlineFlexLexer extends FlexAdapter {
    public RedlineFlexLexer() {
        super(new RedlineLexer((Reader) null));
    }
}
