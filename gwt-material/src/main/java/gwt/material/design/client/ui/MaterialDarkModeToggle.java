package gwt.material.design.client.ui;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.theme.dark.ColorScheme;
import gwt.material.design.client.theme.dark.DarkThemeManager;
import gwt.material.design.client.theme.dark.HasDarkMode;

public class MaterialDarkModeToggle extends MaterialIcon implements HasDarkMode {

    protected DarkThemeManager darkThemeManager;

    public MaterialDarkModeToggle() {
        darkThemeManager = new DarkThemeManager();
    }

    @Override
    protected void onLoad() {
        super.onLoad();


        darkThemeManager.setCallback(colorScheme -> setIconType(colorScheme == ColorScheme.DARK ? IconType.BRIGHTNESS_7 : IconType.BRIGHTNESS_4));
        darkThemeManager.load();
        registerHandler(addClickHandler(event -> setDarkMode(!isDarkMode())));
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        darkThemeManager.unload();
    }

    @Override
    public void setDarkMode(boolean darkMode) {
        darkThemeManager.setDarkMode(darkMode);
    }

    @Override
    public boolean isDarkMode() {
        return darkThemeManager.isDarkMode();
    }
}
