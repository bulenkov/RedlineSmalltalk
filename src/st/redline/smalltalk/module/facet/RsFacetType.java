package st.redline.smalltalk.module.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetType;
import com.intellij.facet.FacetTypeId;
import com.intellij.openapi.module.JavaModuleType;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleType;
import st.redline.smalltalk.SmalltalkIcons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Konstantin Bulenkov
 */
public class RsFacetType extends FacetType<RsFacet, RsFacetConfiguration> {
  public final static FacetTypeId<RsFacet> FACET_TYPE_ID = new FacetTypeId<RsFacet>("RedlineSmalltalk");

  public RsFacetType() {
    super(FACET_TYPE_ID, RsModuleSettings.FACET_ID, RsModuleSettings.FACET_NAME);
  }

  @Override
  public RsFacetConfiguration createDefaultConfiguration() {
    return new RsFacetConfiguration();
  }

  @Override
  public RsFacet createFacet(@NotNull final Module module,
                             final String name,
                             @NotNull final RsFacetConfiguration configuration,
                             @Nullable final Facet underlyingFacet) {
    return new RsFacet(this, module, name, configuration, underlyingFacet);
  }

  @Override
  public boolean isSuitableModuleType(final ModuleType moduleType) {
    return moduleType instanceof JavaModuleType;
  }

  @Override
  public Icon getIcon() {
    return SmalltalkIcons.Redline;
  }

  public static RsFacetType getInstance() {
    return findInstance(RsFacetType.class);
  }

}
