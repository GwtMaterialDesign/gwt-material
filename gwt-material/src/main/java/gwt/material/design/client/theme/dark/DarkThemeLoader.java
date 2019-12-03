package gwt.material.design.client.theme.dark;

import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.resources.client.TextResource;

public abstract class DarkThemeLoader {

    protected StyleElement styleElement = null;
    protected TextResource resource;

    public DarkThemeLoader(TextResource resource) {
        this.resource = resource;
    }

    public void load() {
        styleElement = StyleInjector.injectStylesheet(resource.getText());
    }

    public void unload() {
        if (styleElement != null) {
            styleElement.removeFromParent();
        }
    }
}
