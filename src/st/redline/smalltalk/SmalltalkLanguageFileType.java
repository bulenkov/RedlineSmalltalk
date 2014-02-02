package st.redline.smalltalk;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class SmalltalkLanguageFileType extends LanguageFileType {

    public static final LanguageFileType INSTANCE = new SmalltalkLanguageFileType();

    public SmalltalkLanguageFileType() {
        super(SmalltalkLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Smalltalk";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Smalltalk";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "st";
    }

    @Override
    public Icon getIcon() {
        return SmalltalkIcons.Smalltalk;
    }
}
