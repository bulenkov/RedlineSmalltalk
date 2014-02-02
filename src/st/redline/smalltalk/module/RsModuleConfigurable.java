package st.redline.smalltalk.module;

import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.roots.ModifiableModelsProvider;
import com.intellij.openapi.roots.ModifiableRootModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class RsModuleConfigurable extends FrameworkSupportInModuleConfigurable {
  public static final String REDLINE_SMALLTALK_SDK_KEY = "REDLINE_SMALLTALK_SDK_NAME";
  private RsSdkPanel myRsSdkPanel = new RsSdkPanel();

  @Nullable
  @Override
  public JComponent createComponent() {
    return myRsSdkPanel;
  }

  @Override
  public void addSupport(@NotNull Module module, @NotNull ModifiableRootModel model, @NotNull ModifiableModelsProvider provider) {
    module.setOption(REDLINE_SMALLTALK_SDK_KEY, myRsSdkPanel.getSdkName());
  }
}
