package gwt.material.design.client.accessibility;

import com.google.gwt.resources.client.TextResource;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.MaterialDesign;
import gwt.material.design.client.resources.MaterialDebugResources;
import gwt.material.design.client.resources.MaterialResources;

public class AccessibilityControl {

    protected final String FOCUS_VISIBILITY = "js-focus-visible";
    protected TextResource resource;
    protected boolean loaded = false;

    public void load(boolean debug) {
        if (!loaded) {
            if (debug) {
                resource = MaterialDebugResources.INSTANCE.focusVisibleJsDebug();
            } else {
                resource = MaterialResources.INSTANCE.focusVisibleJs();
            }
            MaterialDesign.injectDebugJs(resource);

            if (RootPanel.get().isAttached() && !RootPanel.get().getElement().hasClassName(FOCUS_VISIBILITY)) {
                RootPanel.get().addStyleName(FOCUS_VISIBILITY);
            }
        }
    }

    public void unload() {
        RootPanel.get().removeStyleName(FOCUS_VISIBILITY);
        loaded = false;
    }
}
