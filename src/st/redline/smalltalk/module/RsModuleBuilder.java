package st.redline.smalltalk.module;

import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetType;
import com.intellij.facet.FacetTypeRegistry;
import com.intellij.facet.ModifiableFacetModel;
import com.intellij.ide.util.projectWizard.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.roots.ContentEntry;
import com.intellij.openapi.roots.ModifiableRootModel;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.openapi.roots.ui.configuration.ModulesProvider;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import st.redline.smalltalk.SmalltalkIcons;
import st.redline.smalltalk.module.facet.RsFacet;
import st.redline.smalltalk.module.facet.RsFacetType;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Konstantin Bulenkov
 */
public class RsModuleBuilder extends JavaModuleBuilder implements ModuleBuilderListener {
  private Sdk mySdk;

  public RsModuleBuilder() {
    addListener(this);
  }

  @Override
  public String getBuilderId() {
    return "smalltalk";
  }

  @Override
  public Icon getBigIcon() {
    return null;
  }

  @Override
  public Icon getNodeIcon() {
    return SmalltalkIcons.Redline;
  }

  @Override
  public String getDescription() {
    if (ProjectJdkTable.getInstance().getSdksOfType(RsSdkType.getInstance()).isEmpty()) {
      return "<html><body>Before you start make sure you have Redline Smalltalk installed." +
          "<br/>Download <a href='https://github.com/redline-smalltalk/redline-smalltalk.github.com/raw/master/assets/redline-deploy.zip'>the latest version</a>" +
          "<br/>Unpack the zip file to any folder and select it as Redline SDK</body></html>";
    } else {
      return "Redline Smalltalk module";
    }
  }

  @Override
  public String getPresentableName() {
    return "Redline Smalltalk Module";
  }

  @Override
  public String getGroupName() {
    return "Smalltalk";
  }

  @Override
  public ModuleWizardStep[] createWizardSteps(WizardContext wizardContext, ModulesProvider modulesProvider) {
    return new ModuleWizardStep[]{new RsModuleWizardStep(this)};
  }

  public void moduleCreated(@NotNull final Module module) {
    if (mySdk != null && mySdk.getSdkType() instanceof RsSdkType) {
      setupFacet(module, mySdk);
      VirtualFile[] roots = ModuleRootManager.getInstance(module).getSourceRoots();
      if (roots.length == 1) {
        VirtualFile srcRoot = roots[0];
        if (srcRoot.getName().equals("smalltalk")) {
          VirtualFile main = srcRoot.getParent();
          if (main != null && "main".equals(main.getName())) {
            final VirtualFile src = main.getParent();
            if (src != null) {
              ApplicationManager.getApplication().runWriteAction(new Runnable() {
                @Override
                public void run() {
                  try {
                    VirtualFile test = src.createChildDirectory(this, "test");
                    if (test != null) {
                      VirtualFile testSrc = test.createChildDirectory(this, "smalltalk");
                      ModifiableRootModel model = ModuleRootManager.getInstance(module).getModifiableModel();
                      ContentEntry entry = findContentEntry(model, testSrc);
                      if (entry != null) {
                        entry.addSourceFolder(testSrc, true);
                        model.commit();
                      }
                    }
                  } catch (IOException e) {//
                  }
                }
              });
            }
          }
        }
      }

    }
  }

  @Nullable
  public static ContentEntry findContentEntry(@NotNull ModuleRootModel model, @NotNull VirtualFile vFile) {
    final ContentEntry[] contentEntries = model.getContentEntries();
    for (ContentEntry contentEntry : contentEntries) {
      final VirtualFile contentEntryFile = contentEntry.getFile();
      if (contentEntryFile != null && VfsUtilCore.isAncestor(contentEntryFile, vFile, false)) {
        return contentEntry;
      }
    }
    return null;
  }

  public static void setupFacet(Module module, Sdk sdk) {
    final String facetId = RsFacetType.getInstance().getStringId();
    if (!StringUtil.isEmptyOrSpaces(facetId)) {

      final FacetManager facetManager = FacetManager.getInstance(module);
      final FacetType<?, ?> type = FacetTypeRegistry.getInstance().findFacetType(facetId);

      if (type != null) {

        if (facetManager.getFacetByType(type.getId()) == null) {
          final ModifiableFacetModel model = facetManager.createModifiableModel();

          final RsFacet facet = (RsFacet) facetManager.addFacet(type, type.getDefaultFacetName(), null);
          facet.getConfiguration().setSdk(sdk);
          model.addFacet(facet);
          model.commit();
        }
      }
    }
  }


  public void setSdk(Sdk sdk) {
    mySdk = sdk;
  }

  @Nullable
  @Override
  public ModuleWizardStep modifySettingsStep(SettingsStep settingsStep) {
    JavaSettingsStep step = (JavaSettingsStep) super.modifySettingsStep(settingsStep);
    if (step != null) {
      step.setSourcePath("src" + File.separator + "main" + File.separator + "smalltalk");
    }
    return step;
  }

  @Override
  protected List<WizardInputField> getAdditionalFields() {
    return Arrays.<WizardInputField>asList(new RsWizardInputField());
  }
}
