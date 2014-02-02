package st.redline.smalltalk.module.facet;

import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import st.redline.smalltalk.module.RsSdkPanel;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class RsFacetEditorTab extends FacetEditorTab {

  private final RsSdkPanel mySdkPanel;
  private final RsModuleSettings mySettings;

  public RsFacetEditorTab(RsModuleSettings settings) {
    mySettings = settings;
    mySdkPanel = new RsSdkPanel();
  }

  @Nls
  @Override
  public String getDisplayName() {
    return "Redline Smalltalk";
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    return mySdkPanel;
  }

  @Override
  public boolean isModified() {
    return !StringUtil.equals(mySettings.redlineSmalltalkSdkName, mySdkPanel.getSdkName());
  }

  @Override
  public void apply() throws ConfigurationException {
    mySettings.redlineSmalltalkSdkName = mySdkPanel.getSdkName();
  }

  @Override
  public void reset() {
    final Sdk sdk = ProjectJdkTable.getInstance().findJdk(mySettings.redlineSmalltalkSdkName);
    if (sdk != null) {
      mySdkPanel.setSdk(sdk);
    }
  }

  @Override
  public void disposeUIResources() {
  }
}