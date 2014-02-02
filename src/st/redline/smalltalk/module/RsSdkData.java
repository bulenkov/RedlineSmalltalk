package st.redline.smalltalk.module;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.projectRoots.SdkAdditionalData;
import com.intellij.util.xmlb.XmlSerializerUtil;

/**
 * @author Konstantin Bulenkov
 */
@SuppressWarnings("UnusedDeclaration")
public class RsSdkData implements SdkAdditionalData, PersistentStateComponent<RsSdkData> {
  private String homePath = "";
  private String version = "";

  public RsSdkData() {
  }

  public RsSdkData(String homePath, String version) {
    this.homePath = homePath;
    this.version = version;
  }

  public String getHomePath() {
    return homePath;
  }

  public String getVersion() {
    return version;
  }

  @SuppressWarnings({"CloneDoesntCallSuperClone"})
  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public RsSdkData getState() {
    return this;
  }

  public void loadState(RsSdkData state) {
    XmlSerializerUtil.copyBean(state, this);
  }
}
