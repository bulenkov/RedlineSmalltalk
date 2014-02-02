package st.redline.smalltalk.module;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.projectRoots.ui.ProjectJdksEditor;
import com.intellij.ui.ColoredListCellRenderer;
import com.intellij.ui.ComboboxWithBrowseButton;
import com.intellij.ui.SimpleTextAttributes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Konstantin Bulenkov
 */
public class RsSdkComboBox extends ComboboxWithBrowseButton {

  public RsSdkComboBox() {
    getComboBox().setRenderer(new ColoredListCellRenderer() {
      @Override
      protected void customizeCellRenderer(JList list, Object value, int index, boolean selected, boolean hasFocus) {
        if (value instanceof Sdk) {
          append(((Sdk) value).getName());
        } else {
          append("Select Redline SDK", SimpleTextAttributes.ERROR_ATTRIBUTES);
        }
      }
    });
    addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Sdk selectedSdk = getSelectedSdk();
        final Project project = ProjectManager.getInstance().getDefaultProject();
        ProjectJdksEditor editor = new ProjectJdksEditor(selectedSdk, project, RsSdkComboBox.this);
        editor.show();
        if (editor.isOK()) {
          selectedSdk = editor.getSelectedJdk();
          updateSdkList(selectedSdk, false);
        }
      }
    });
    updateSdkList(null, true);
  }

  public void updateSdkList(Sdk sdkToSelect, boolean selectAnySdk) {
    final List<Sdk> sdkList = ProjectJdkTable.getInstance().getSdksOfType(RsSdkType.getInstance());
    if (selectAnySdk && sdkList.size() > 0) {
      sdkToSelect = sdkList.get(0);
    }
    sdkList.add(0, null);
    getComboBox().setModel(new DefaultComboBoxModel(sdkList.toArray(new Sdk[sdkList.size()])));
    getComboBox().setSelectedItem(sdkToSelect);
  }

  public Sdk getSelectedSdk() {
    return (Sdk) getComboBox().getSelectedItem();
  }
}
