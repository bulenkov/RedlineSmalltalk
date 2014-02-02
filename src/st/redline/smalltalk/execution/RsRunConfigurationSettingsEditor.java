package st.redline.smalltalk.execution;

import com.intellij.execution.ExecutionBundle;
import com.intellij.execution.application.ApplicationConfiguration;
import com.intellij.execution.ui.AlternativeJREPanel;
import com.intellij.execution.ui.CommonJavaParametersPanel;
import com.intellij.execution.ui.ConfigurationModuleSelector;
import com.intellij.execution.util.JreVersionDetector;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComponentWithBrowseButton;
import com.intellij.openapi.ui.LabeledComponent;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.PanelWithAnchor;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import st.redline.smalltalk.util.SmalltalkFileBrowser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
* @author Konstantin Bulenkov
*/
public class RsRunConfigurationSettingsEditor extends SettingsEditor<ApplicationConfiguration> implements PanelWithAnchor {
  private CommonJavaParametersPanel myCommonProgramParameters;
  private LabeledComponent<ComponentWithBrowseButton> myMainClass;
  private LabeledComponent<JComboBox> myModule;
  private JPanel myWholePanel;

  private final ConfigurationModuleSelector myModuleSelector;
  private AlternativeJREPanel myAlternativeJREPanel;
  private JCheckBox myShowSwingInspectorCheckbox;
  private final JreVersionDetector myVersionDetector;
  private final Project myProject;
  private JComponent myAnchor;

  public RsRunConfigurationSettingsEditor(final Project project) {
    myProject = project;

    myModuleSelector = new ConfigurationModuleSelector(project, myModule.getComponent());
    myCommonProgramParameters.setModuleContext(myModuleSelector.getModule());
    myModule.getComponent().addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        myCommonProgramParameters.setModuleContext(myModuleSelector.getModule());
      }
    });
    new SmalltalkFileBrowser(myProject, myModuleSelector).setField(getMainClassField());
    myVersionDetector = new JreVersionDetector();

    myAnchor = UIUtil.mergeComponentsWithAnchor(myMainClass, myCommonProgramParameters, myAlternativeJREPanel, myModule);
  }

  public void applyEditorTo(final ApplicationConfiguration configuration) throws ConfigurationException {
    myCommonProgramParameters.applyTo(configuration);
    myModuleSelector.applyTo(configuration);
    configuration.MAIN_CLASS_NAME = getMainClassField().getText();
    configuration.ALTERNATIVE_JRE_PATH = myAlternativeJREPanel.getPath();
    configuration.ALTERNATIVE_JRE_PATH_ENABLED = myAlternativeJREPanel.isPathEnabled();
    configuration.ENABLE_SWING_INSPECTOR = (myVersionDetector.isJre50Configured(configuration) || myVersionDetector.isModuleJre50Configured(configuration)) && myShowSwingInspectorCheckbox.isSelected();

    updateShowSwingInspector(configuration);
  }

  public void resetEditorFrom(final ApplicationConfiguration configuration) {
    myCommonProgramParameters.reset(configuration);
    myModuleSelector.reset(configuration);
    getMainClassField().setText(configuration.MAIN_CLASS_NAME);
    myAlternativeJREPanel.init(configuration.ALTERNATIVE_JRE_PATH, configuration.ALTERNATIVE_JRE_PATH_ENABLED);

    updateShowSwingInspector(configuration);
  }

  private void updateShowSwingInspector(final ApplicationConfiguration configuration) {
    if (myVersionDetector.isJre50Configured(configuration) || myVersionDetector.isModuleJre50Configured(configuration)) {
      myShowSwingInspectorCheckbox.setEnabled(true);
      myShowSwingInspectorCheckbox.setSelected(configuration.ENABLE_SWING_INSPECTOR);
      myShowSwingInspectorCheckbox.setText(ExecutionBundle.message("show.swing.inspector"));
    }
    else {
      myShowSwingInspectorCheckbox.setEnabled(false);
      myShowSwingInspectorCheckbox.setSelected(false);
      myShowSwingInspectorCheckbox.setText(ExecutionBundle.message("show.swing.inspector.disabled"));
    }
  }

  public TextFieldWithBrowseButton getMainClassField() {
    return (TextFieldWithBrowseButton) myMainClass.getComponent();
  }

  public CommonJavaParametersPanel getCommonProgramParameters() {
    return myCommonProgramParameters;
  }

  @NotNull
  public JComponent createEditor() {
    return myWholePanel;
  }

  public void disposeEditor() {
  }

  private void createUIComponents() {
    myMainClass = new LabeledComponent<ComponentWithBrowseButton>();
    myMainClass.setComponent(new TextFieldWithBrowseButton());
  }

  @Override
  public JComponent getAnchor() {
    return myAnchor;
  }

  @Override
  public void setAnchor(@Nullable JComponent anchor) {
    this.myAnchor = anchor;
    myMainClass.setAnchor(anchor);
    myCommonProgramParameters.setAnchor(anchor);
    myAlternativeJREPanel.setAnchor(anchor);
    myModule.setAnchor(anchor);
  }
}
