package st.redline.smalltalk.module;

import com.intellij.openapi.module.ModuleType;
import st.redline.smalltalk.SmalltalkIcons;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class RsModuleType extends ModuleType<RsModuleBuilder> {
  private static final RsModuleType INSTANCE = new RsModuleType();

  public RsModuleType() {
    super("REDLINE_MODULE");
  }

  @Override
  public RsModuleBuilder createModuleBuilder() {
    return new RsModuleBuilder();
  }



  @Override
  public String getName() {
    return "Redline Smalltalk";
  }

  @Override
  public String getDescription() {
    return "Add support for Redline Smalltalk";
  }

  @Override
  public Icon getBigIcon() {
    return null;
  }

  @Override
  public Icon getNodeIcon(@Deprecated boolean isOpened) {
    return SmalltalkIcons.Redline;
  }

  public static RsModuleType getInstance() {
    return INSTANCE;
  }
}
