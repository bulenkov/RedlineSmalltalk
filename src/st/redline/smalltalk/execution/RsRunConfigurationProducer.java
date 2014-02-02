package st.redline.smalltalk.execution;

import com.intellij.execution.Location;
import com.intellij.execution.PsiLocation;
import com.intellij.execution.RunnerAndConfigurationSettings;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.configurations.LocatableConfiguration;
import com.intellij.execution.junit.RuntimeConfigurationProducer;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.containers.HashSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collections;
import java.util.Set;

/**
 * @author Konstantin Bulenkov
 */
public class RsRunConfigurationProducer extends RuntimeConfigurationProducer {
  private PsiElement mySourceElement;

  public RsRunConfigurationProducer() {
    super(RsRunConfigurationType.getInstance());
  }

  @Override
  public PsiElement getSourceElement() {
    return mySourceElement;
  }

  @Override
  protected RunnerAndConfigurationSettings createConfigurationByElement(Location location, ConfigurationContext context) {
    if (!(location instanceof PsiLocation)) return null;

    PsiElement element = location.getPsiElement();
    PsiFile containingFile = element.getContainingFile();

    if (containingFile == null || !containingFile.getName().toLowerCase().endsWith(".st")) return null;

    VirtualFile file = containingFile.getOriginalFile().getVirtualFile();

    if (file == null) return null;

    final RunnerAndConfigurationSettings settings = cloneTemplateConfiguration(location.getProject(), context);
    final LocatableConfiguration runConfig = (LocatableConfiguration) settings.getConfiguration();
    if (!(runConfig instanceof RsRunConfiguration)) {
      return null;
    }

    final RsRunConfiguration commandLineRunConfiguration = ((RsRunConfiguration) runConfig);
    if (!setupRunConfiguration(commandLineRunConfiguration, containingFile)) {
      return null;
    }

    mySourceElement = location.getPsiElement();
    settings.setName(file.getNameWithoutExtension());
    return settings;
  }

  private static boolean setupRunConfiguration(RsRunConfiguration configuration, PsiFile psiFile) {
    VirtualFile file = psiFile != null ? psiFile.getOriginalFile().getVirtualFile() : null;
    if (file == null) {
      return false;
    }
    Module module = ModuleUtilCore.findModuleForFile(file, psiFile.getProject());

    if (!(module != null && ModuleUtil.moduleContainsFile(module, file, false))) {
      return false;
    }

    VirtualFile root = findSourceRoot(module, file);
    if (root == null) return false;
    String path = FileUtil.getRelativePath(new File(root.getPath()), new File(file.getParent().getPath()));
    if (".".equals(path)) {
      path = "";
    }
    if (!StringUtil.isEmpty(path)) {
      path += ".";
    }

    configuration.setMainClass(path + file.getNameWithoutExtension());
    configuration.setModule(module);
    return true;
  }

  @Nullable
  public static VirtualFile findSourceRoot(@NotNull Module module, VirtualFile file) {
    final Set<VirtualFile> sourceRoots = new HashSet<VirtualFile>();
    Collections.addAll(sourceRoots, ModuleRootManager.getInstance(module).getSourceRoots());

    while (file != null) {
      if (sourceRoots.contains(file)) {
        return file;
      }
      file = file.getParent();
    }
    return null;
  }


  @Nullable
  @Override
  protected RunnerAndConfigurationSettings findExistingByElement(Location location,
                                                                 @NotNull RunnerAndConfigurationSettings[] existingConfigurations,
                                                                 ConfigurationContext context) {
    final PsiElement element = location.getPsiElement();
    final PsiFile containingFile = element.getContainingFile();
    VirtualFile file = containingFile.getOriginalFile().getVirtualFile();
    assert file != null;
    final String name = file.getNameWithoutExtension();
    return ContainerUtil.find(existingConfigurations, new Condition<RunnerAndConfigurationSettings>() {
      @Override
      public boolean value(RunnerAndConfigurationSettings settings) {
        return name.equals(settings.getName());
      }
    });
  }

  @Override
  public int compareTo(@NotNull Object o) {
    return PREFERED;
  }
}

