package st.redline.smalltalk.module;

import com.intellij.ide.util.projectWizard.ModuleWizardStep;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.text.StringUtil;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class RsModuleWizardStep extends ModuleWizardStep {
  private final RsSdkPanel sdkPanel = new RsSdkPanel();
  private RsModuleBuilder myBuilder;

  public RsModuleWizardStep(RsModuleBuilder builder) {
    myBuilder = builder;
  }

  @Override
  public JComponent getComponent() {
    return sdkPanel;
  }

  @Override
  public void updateDataModel() {
    myBuilder.setSdk(sdkPanel.getSdk());
  }


  @Override
  public boolean validate() throws ConfigurationException {
    if (StringUtil.isEmpty(sdkPanel.getSdkName())) {
      throw new ConfigurationException("Specify Redline Smalltalk SDK");
    }
    return super.validate();
  }
}
