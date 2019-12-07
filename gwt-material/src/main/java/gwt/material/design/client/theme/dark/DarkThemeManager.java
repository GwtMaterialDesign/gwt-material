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

    public DarkThemeLoader getLoader(Class<? extends DarkThemeLoader> clazz) {
        return map.get(clazz);
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
