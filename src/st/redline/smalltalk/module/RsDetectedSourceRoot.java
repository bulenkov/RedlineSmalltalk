package st.redline.smalltalk.module;

import com.intellij.ide.util.projectWizard.importSources.DetectedSourceRoot;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
* @author Konstantin Bulenkov
*/
public class RsDetectedSourceRoot extends DetectedSourceRoot {
  public RsDetectedSourceRoot(File root) {
    super(root, "");
  }

  @NotNull
  @Override
  public String getRootTypeName() {
    return "Smalltalk";
  }
}
