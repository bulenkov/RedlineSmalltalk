package st.redline.smalltalk.execution;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.ConfigurationTypeUtil;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import st.redline.smalltalk.SmalltalkIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class RsRunConfigurationType implements ConfigurationType {

  public static RsRunConfigurationType getInstance() {
    return ConfigurationTypeUtil.findConfigurationType(RsRunConfigurationType.class);
  }

  //for backward compatibility reasons
  private static final String FACTORY_NAME = "Smalltalk console";

  private final ConfigurationFactory myFactory = new ConfigurationFactory(this) {
    @Override
    public RunConfiguration createTemplateConfiguration(Project project) {
      return new RsRunConfiguration("", project, this);
    }

    @Override
    public String getName() {
      return FACTORY_NAME;
    }
  };

  @Override
  public String getDisplayName() {
    return "Redline Smalltalk";
  }

  @Override
  public String getConfigurationTypeDescription() {
    return "Run Smalltalk in console";
  }

  @Override
  public Icon getIcon() {
    return SmalltalkIcons.Redline;
  }

  @Override
  public ConfigurationFactory[] getConfigurationFactories() {
    return new ConfigurationFactory[]{myFactory};
  }

  @Override
  @NotNull
  public String getId() {
    //for backward compatibility reasons
    return "SmalltalkRunConfigurationType";
  }

}
