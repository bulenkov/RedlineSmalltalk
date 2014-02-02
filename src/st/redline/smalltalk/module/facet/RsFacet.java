package st.redline.smalltalk.module.facet;

import com.intellij.facet.Facet;
import com.intellij.facet.FacetManager;
import com.intellij.facet.FacetType;
import com.intellij.openapi.module.Module;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Konstantin Bulenkov
 */
public class RsFacet extends Facet<RsFacetConfiguration> {
  public RsFacet(@NotNull final FacetType facetType,
                 @NotNull final Module module,
                 final String name,
                 @NotNull final RsFacetConfiguration configuration,
                 Facet underlyingFacet) {
    super(facetType, module, name, configuration, underlyingFacet);
  }

  @Nullable
  public static RsFacet getInstance(Module module) {
    return FacetManager.getInstance(module).getFacetByType(RsFacetType.FACET_TYPE_ID);
  }
}

