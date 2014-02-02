package st.redline.smalltalk.module;

import com.intellij.framework.FrameworkTypeEx;
import com.intellij.framework.addSupport.FrameworkSupportInModuleConfigurable;
import com.intellij.framework.addSupport.FrameworkSupportInModuleProvider;
import com.intellij.ide.util.frameworkSupport.FrameworkSupportModel;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.ModuleType;
import org.jetbrains.annotations.NotNull;

/**
 * @author Konstantin Bulenkov
 */
public class RsModuleProvider extends FrameworkSupportInModuleProvider {
  public RsModuleProvider() {

  }

  @NotNull
  @Override
  public FrameworkTypeEx getFrameworkType() {
    return FrameworkTypeEx.EP_NAME.findExtension(RsFrameworkType.class);
  }

  @NotNull
  @Override
  public FrameworkSupportInModuleConfigurable createConfigurable(@NotNull FrameworkSupportModel model) {
    return new RsModuleConfigurable();
  }

  @Override
  public boolean isEnabledForModuleType(@NotNull ModuleType moduleType) {
    return moduleType instanceof JavaModuleType;
  }
}
