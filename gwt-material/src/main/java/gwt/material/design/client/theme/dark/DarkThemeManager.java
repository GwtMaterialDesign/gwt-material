package gwt.material.design.client.theme.dark;

import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;
import gwt.material.design.client.js.MediaQueryList;
import gwt.material.design.client.js.Window;
import gwt.material.design.client.resources.DarkThemeResources;
import gwt.material.design.jquery.client.api.Functions;

//TODO: Move to gwt-material-themes after the POC
public class DarkThemeManager implements HasDarkMode {

    protected StyleElement styleElement = null;
    protected boolean darkMode;
    protected Functions.Func1<ColorScheme> callback;

    public void load() {
        MediaQueryList mediaQueryList = Window.getMediaQueryList("(prefers-color-scheme: dark)");
        setDarkMode(mediaQueryList.matches);
        mediaQueryList.addListener(mediaQueryEvent -> setDarkMode(mediaQueryEvent.matches));
    }

    public void unload() {

    }

    @Override
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;

        if (darkMode) {
            styleElement = StyleInjector.injectStylesheet(DarkThemeResources.INSTANCE.darkCss().getText());
        } else {
            if (styleElement != null) {
                styleElement.removeFromParent();
            }
        }

        if (callback != null) {
            callback.call(darkMode ? ColorScheme.DARK : ColorScheme.LIGHT);
        }
    }

    public Functions.Func1<ColorScheme> getCallback() {
        return callback;
    }

    public void setCallback(Functions.Func1<ColorScheme> callback) {
        this.callback = callback;
    }

    @Override
    public boolean isDarkMode() {
        return darkMode;
    }
}
