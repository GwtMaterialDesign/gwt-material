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
