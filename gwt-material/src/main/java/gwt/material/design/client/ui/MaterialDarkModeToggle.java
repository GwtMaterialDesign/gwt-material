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
import gwt.material.design.client.js.MediaQueryList;
import gwt.material.design.client.js.Window;
import gwt.material.design.client.theme.dark.*;

public class MaterialDarkModeToggle extends MaterialIcon implements HasDarkMode, HasColorSchemeChangeHandler {

    protected DarkThemeManager manager = DarkThemeManager.get();

    public MaterialDarkModeToggle() {
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        MediaQueryList mediaQueryList = Window.getMediaQueryList("(prefers-color-scheme: dark)");
        setDarkMode(mediaQueryList.matches);
        mediaQueryList.addListener(mediaQueryEvent -> setDarkMode(mediaQueryEvent.matches));
        registerHandler(addClickHandler(event -> setDarkMode(!isDarkMode())));
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        manager.unload();
    }

    @Override
    public void setDarkMode(boolean darkMode) {
        setIconType(darkMode ? IconType.BRIGHTNESS_7 : IconType.BRIGHTNESS_4);
        manager.setDarkMode(darkMode);
        ColorSchemeChangeEvent.fire(this, darkMode ? ColorScheme.DARK : ColorScheme.LIGHT);
    }

    @Override
    public boolean isDarkMode() {
        return manager.isDarkMode();
    }

    @Override
    public HandlerRegistration addColorSchemeChangeHandler(ColorSchemeChangeEvent.ColorSchemeChangeHandler handler) {
        return addHandler(handler, ColorSchemeChangeEvent.TYPE);
    }
}
