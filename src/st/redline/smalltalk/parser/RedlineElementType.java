package st.redline.smalltalk.parser;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import st.redline.smalltalk.SmalltalkLanguage;

/**
 * @author Konstantin Bulenkov
 */
public class RedlineElementType extends IElementType {
    public RedlineElementType(@NotNull @NonNls String debugName) {
        super(debugName, SmalltalkLanguage.INSTANCE);
    }
}
