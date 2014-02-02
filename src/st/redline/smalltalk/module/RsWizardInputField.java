package st.redline.smalltalk.module;

import com.intellij.ide.util.projectWizard.WizardInputField;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.Condition;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class RsWizardInputField extends WizardInputField {
  private final RsSdkComboBox myCombo;

  protected RsWizardInputField() {
    super("REDLINE_SDK", findMostRecentSdkPath());
    myCombo = new RsSdkComboBox();
  }

  private static String findMostRecentSdkPath() {
    Sdk sdk = ProjectJdkTable.getInstance().findMostRecentSdk(new Condition<Sdk>() {
      @Override
      public boolean value(Sdk sdk) {
        return sdk.getSdkType() instanceof RsSdkType;
      }
    });
    return sdk != null ? sdk.getName() : null;
  }

  @Override
  public String getLabel() {
    return "Redline Smalltalk SDK";
  }

  @Override
  public JComponent getComponent() {
    return myCombo;
  }

  @Override
  public String getValue() {
    Sdk sdk = myCombo.getSelectedSdk();
    return sdk == null ? "" : sdk.getHomePath();
  }
}
