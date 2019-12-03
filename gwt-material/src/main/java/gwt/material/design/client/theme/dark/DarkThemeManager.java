package gwt.material.design.client.theme.dark;

import gwt.material.design.client.js.MediaQueryList;
import gwt.material.design.client.js.Window;
import gwt.material.design.jquery.client.api.Functions;

import java.util.HashMap;
import java.util.Map;

public class DarkThemeManager implements HasDarkMode {

    protected static boolean darkMode;
    protected static Functions.Func1<ColorScheme> callback;
    protected static DarkThemeManager instance;
    protected static Map<Class<? extends DarkThemeLoader>, DarkThemeLoader> map = new HashMap<>();

    public DarkThemeManager register(DarkThemeLoader darkThemeLoader) {
        map.put(darkThemeLoader.getClass(), darkThemeLoader);
        return this;
    }

    public DarkThemeManager unregister(Class<? extends DarkThemeLoader> clazz) {
        map.remove(clazz);
        return this;
    }

    public void load() {
        MediaQueryList mediaQueryList = Window.getMediaQueryList("(prefers-color-scheme: dark)");
        setDarkMode(mediaQueryList.matches);
        mediaQueryList.addListener(mediaQueryEvent -> setDarkMode(mediaQueryEvent.matches));
    }

    public void unload() {
        if (map != null) {
            map.forEach((aClass, darkThemeLoader) -> darkThemeLoader.unload());
        }
    }

    public static Map<Class<? extends DarkThemeLoader>, DarkThemeLoader> getMap() {
        return map;
    }

    public static DarkThemeManager get() {
        if (instance == null) {
            instance = new DarkThemeManager();
        }
        return instance;
    }

    @Override
    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;

        map.forEach((aClass, darkThemeLoader) -> {
            if (darkMode) {
                darkThemeLoader.load();
            } else {
                darkThemeLoader.unload();
            }
        });
    }

    @Override
    public boolean isDarkMode() {
        return darkMode;
    }
}
