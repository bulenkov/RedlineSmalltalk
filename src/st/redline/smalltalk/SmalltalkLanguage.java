package st.redline.smalltalk;

import com.intellij.lang.Language;

/**
 * @author Konstantin Bulenkov
 */
public class SmalltalkLanguage extends Language {
    public static final SmalltalkLanguage INSTANCE = new SmalltalkLanguage();

    public SmalltalkLanguage() {
        super("Smalltalk", "text/smalltalk");
    }
}
