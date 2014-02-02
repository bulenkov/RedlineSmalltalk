package st.redline.smalltalk.module;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.AccessToken;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserFactory;
import com.intellij.openapi.fileChooser.PathChooserDialog;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.labels.ActionLink;
import com.intellij.util.download.DownloadableFileDescription;
import com.intellij.util.download.DownloadableFileService;
import com.intellij.util.download.FileDownloader;
import com.intellij.util.io.ZipUtil;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Konstantin Bulenkov
 */
public class RsSdkPanel extends JPanel {

  public static final String LAST_USED_REDLINE_HOME = "LAST_USED_REDLINE_HOME";
  private ActionLink myDownloadLink;
  private JPanel myRoot;
  private RsSdkComboBox mySdkComboBox;

  public RsSdkPanel() {
    super(new BorderLayout());
    add(myRoot, BorderLayout.CENTER);
  }

  private void createUIComponents() {
    myDownloadLink = new ActionLink("Download and Install Redline Smalltalk", new AnAction() {
      @Override
      public void actionPerformed(AnActionEvent anActionEvent) {
        FileChooserDescriptor descriptor = new FileChooserDescriptor(false, true, false, false, false, false);
        PathChooserDialog pathChooser = FileChooserFactory.getInstance()
            .createPathChooser(descriptor, null, RsSdkPanel.this);
        pathChooser.choose(VfsUtil.getUserHomeDir(), new FileChooser.FileChooserConsumer() {
          @Override
          public void cancelled() {
          }

          @Override
          public void consume(List<VirtualFile> virtualFiles) {
            if (virtualFiles.size() == 1) {
              VirtualFile dir = virtualFiles.get(0);
              String dirName = dir.getName();
              AccessToken accessToken = ApplicationManager.getApplication().acquireWriteActionLock(RsSdkPanel.class);
              try {
                if (!dirName.toLowerCase().contains("smalltalk") && !dirName.toLowerCase().contains("redline")) {
                try {
                  dir = dir.createChildDirectory(this, "RedlineSmalltalk");
                } catch (IOException e) {//
                }
              }
              DownloadableFileService fileService = DownloadableFileService.getInstance();
              DownloadableFileDescription fileDescription = fileService.createFileDescription("https://github.com/redline-smalltalk/redline-smalltalk.github.com/raw/master/assets/redline-deploy.zip", "redline-deploy.zip");
              FileDownloader downloader = fileService.createDownloader(Arrays.asList(fileDescription), null, RsSdkPanel.this, "redline-deploy.zip");
              VirtualFile[] files = downloader.toDirectory(dir.getPath()).download();
              if (files != null && files.length == 1) {
                try {
                  ZipUtil.extract(VfsUtil.virtualToIoFile(files[0]), VfsUtil.virtualToIoFile(dir), null);
                  PropertiesComponent.getInstance().setValue(LAST_USED_REDLINE_HOME, dir.getPath());
                } catch (IOException e) {
                  e.printStackTrace();
                } finally {
                  dir.refresh(false, true);
                }
              }
              } finally {
                accessToken.finish();
              }
            }
          }
        });
      }
    });
  }

  public String getSdkName() {
    final Sdk selectedSdk = mySdkComboBox.getSelectedSdk();
    return selectedSdk == null ? null : selectedSdk.getName();
  }

  public Sdk getSdk() {
    return mySdkComboBox.getSelectedSdk();
  }

  public void setSdk(Sdk sdk) {
    mySdkComboBox.getComboBox().setSelectedItem(sdk);
  }
}
