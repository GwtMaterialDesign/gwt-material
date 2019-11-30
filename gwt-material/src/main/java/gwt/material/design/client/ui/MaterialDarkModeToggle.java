/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2019 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.ui;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.theme.dark.*;

public class MaterialDarkModeToggle extends MaterialIcon implements HasDarkMode, HasColorSchemeChangeHandler {

    protected DarkThemeManager darkThemeManager;

    public MaterialDarkModeToggle() {
        darkThemeManager = new DarkThemeManager();
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        darkThemeManager.setCallback(colorScheme -> {
            setIconType(colorScheme == ColorScheme.DARK ? IconType.BRIGHTNESS_7 : IconType.BRIGHTNESS_4);
            ColorSchemeChangeEvent.fire(this, colorScheme);
        });
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

    @Override
    public HandlerRegistration addColorSchemeChangeHandler(ColorSchemeChangeEvent.ColorSchemeChangeHandler handler) {
        return addHandler(handler, ColorSchemeChangeEvent.TYPE);
    }
}
