package st.redline.smalltalk.util;

import com.intellij.execution.configuration.BrowseModuleValueActionListener;
import com.intellij.execution.ui.ConfigurationModuleSelector;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

/**
 * @author Konstantin Bulenkov
 */
public class SmalltalkFileBrowser extends BrowseModuleValueActionListener {
  private final ConfigurationModuleSelector myModuleSelector;

  public SmalltalkFileBrowser(final Project project, ConfigurationModuleSelector moduleSelector) {
    super(project);
    myModuleSelector = moduleSelector;
  }

    @Override
    protected String showDialog() {
      final FileChooserDescriptor descriptor = new FileChooserDescriptor(true, false, false, false, false, false) {
        @Override
        public boolean isFileVisible(VirtualFile file, boolean showHiddenFiles) {
          return "st".equalsIgnoreCase(file.getExtension()) || file.isDirectory(); //todo don't show hidden folders if showHiddenFiles == false
        }
      };
      descriptor.setTitle("Choose Smalltalk File");
      String dirPath = getText().replace(".", File.separator);
      if (!StringUtil.isEmpty(dirPath) && !dirPath.endsWith(".st")) {
        dirPath += ".st";
      }
      final Module module = myModuleSelector.getModule();
      VirtualFile[] files = FileChooser.chooseFiles(descriptor, getProject(), module == null ? null : module.getModuleFile().getParent());
      if (files.length == 1) {
        return FileUtil.toSystemDependentName(files[0].getPath());
      }
      return null;
    }
  }

