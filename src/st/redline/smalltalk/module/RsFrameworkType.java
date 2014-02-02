package st.redline.smalltalk.module;

import com.intellij.framework.FrameworkTypeEx;
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider;
import st.redline.smalltalk.SmalltalkIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class RsFrameworkType extends FrameworkTypeEx {
  public static final String ID = "Redline Smalltalk";

  protected RsFrameworkType() {
    super(ID);
  }

  @NotNull
  @Override
  public FrameworkSupportInModuleProvider createProvider() {
    return new RsModuleProvider();
  }

  @NotNull
  @Override
  public String getPresentableName() {
    return "Redline Smalltalk";
  }

  @NotNull
  @Override
  public Icon getIcon() {
    return SmalltalkIcons.Redline;
  }
}
