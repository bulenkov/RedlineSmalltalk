package st.redline.smalltalk.actions;

import com.intellij.ide.IdeBundle;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.CreateFromTemplateAction;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidatorEx;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.util.IncorrectOperationException;
import st.redline.smalltalk.SmalltalkIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Properties;

/**
 * @author Konstantin Bulenkov
 */
public class NewSmalltalkFileAction extends CreateFromTemplateAction<PsiFile> {
  public NewSmalltalkFileAction() {
    super("Smalltalk File", null, SmalltalkIcons.Smalltalk);
  }

  @Override
  protected boolean isAvailable(DataContext dataContext) {
    final Module module = LangDataKeys.MODULE.getData(dataContext);
    final ModuleType moduleType = module == null ? null : ModuleType.get(module);
    final boolean isJavaModule = moduleType instanceof JavaModuleType;
    return super.isAvailable(dataContext) && isJavaModule;
  }

  @Override
  protected String getActionName(PsiDirectory directory, String newName, String templateName) {
    return "Creating File " + newName;
  }

  @Override
  protected void buildDialog(Project project, PsiDirectory directory, CreateFileFromTemplateDialog.Builder builder) {
    builder.setTitle(IdeBundle.message("action.create.new.class"));
    for (FileTemplate fileTemplate : RsFileTemplateUtil.getApplicableTemplates()) {
      final String templateName = fileTemplate.getName();
      final String shortName = RsFileTemplateUtil.getTemplateShortName(templateName);
      final Icon icon = SmalltalkIcons.Smalltalk;
      builder.addKind(shortName, icon, templateName);
    }
    builder.setValidator(new InputValidatorEx() {
      @Override
      public String getErrorText(String inputString) {
        if (inputString.length() > 0 && !StringUtil.isJavaIdentifier(inputString)) {
          return "This is not a valid Smalltalk name";
        }
        return null;
      }

      @Override
      public boolean checkInput(String inputString) {
        return true;
      }

      @Override
      public boolean canClose(String inputString) {
        return !StringUtil.isEmptyOrSpaces(inputString) && getErrorText(inputString) == null;
      }
    });
  }

  @Nullable
  @Override
  protected PsiFile createFile(String className, String templateName, PsiDirectory dir) {
    try {
      return createFile(className, dir, templateName).getContainingFile();
    }
    catch (Exception e) {
      throw new IncorrectOperationException(e.getMessage(), e);
    }
  }

  private static PsiElement createFile(String className, @NotNull PsiDirectory directory, final String templateName)
      throws Exception {
    final Properties props = new Properties(FileTemplateManager.getInstance().getDefaultProperties(directory.getProject()));
    props.setProperty(FileTemplate.ATTRIBUTE_NAME, className);

    final FileTemplate template = FileTemplateManager.getInstance().getInternalTemplate(templateName);

    return FileTemplateUtil.createFromTemplate(template, className, props, directory, NewSmalltalkFileAction.class.getClassLoader());
  }
}
