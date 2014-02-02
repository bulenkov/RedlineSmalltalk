package st.redline.smalltalk.module.facet;

import com.intellij.facet.FacetConfiguration;
import com.intellij.facet.ui.FacetEditorContext;
import com.intellij.facet.ui.FacetEditorTab;
import com.intellij.facet.ui.FacetValidatorsManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.projectRoots.Sdk;
import com.intellij.openapi.util.InvalidDataException;
import com.intellij.openapi.util.WriteExternalException;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;

/**
 * @author Konstantin Bulenkov
 */
public class RsFacetConfiguration implements FacetConfiguration, PersistentStateComponent<RsModuleSettings> {
  private RsModuleSettings mySettings = new RsModuleSettings();

  public FacetEditorTab[] createEditorTabs(final FacetEditorContext editorContext,
                                           final FacetValidatorsManager validatorsManager) {
    return new FacetEditorTab[]{new RsFacetEditorTab(mySettings)};
  }

  public void readExternal(Element element) throws InvalidDataException {
  }

  public void writeExternal(Element element) throws WriteExternalException {
  }

  @Override
  @NotNull
  public RsModuleSettings getState() {
    return mySettings;
  }

  public void setSdk(Sdk sdk) {
    mySettings.redlineSmalltalkSdkName = sdk == null ? null : sdk.getName();
  }

  @Override
  public void loadState(RsModuleSettings state) {
    XmlSerializerUtil.copyBean(state, mySettings);
  }
}
