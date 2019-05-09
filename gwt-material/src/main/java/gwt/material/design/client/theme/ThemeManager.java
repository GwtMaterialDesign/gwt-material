/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.theme;

import com.google.gwt.core.client.GWT;
import gwt.material.design.client.base.MaterialWidget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

@SuppressWarnings("unchecked")
public class
ThemeManager {

    private static SortedSet<Theme> themes;

    private static Map<Class, List<WidgetTheme>> themeCache = new HashMap<>();

    public static void addTheme(Theme theme) {
        if (!getThemes().contains(theme)) {
            getThemes().add(theme);

            for(WidgetTheme widgetTheme : theme.load()) {
                widgetTheme.setTheme(theme);
                addWidgetTheme(theme.getName(), widgetTheme);
            }
        } else {
            GWT.log("Theme '" + theme.getName() + "' has already been " +
                    "loaded (ignoring '"+theme.getClass().getSimpleName()+"').");
        }
    }

    public static SortedSet<Theme> getThemes() {
        if (themes == null) {
            themes = new TreeSet<>();
        }
        return themes;
    }

    public static List<WidgetTheme> getWidgetThemes(MaterialWidget object) {
        return getWidgetThemes(object, object.getClass(), new ArrayList<>());
    }

    public static List<WidgetTheme> getWidgetThemes(MaterialWidget object, Class clazz, List<WidgetTheme> widgetThemes) {
        List<WidgetTheme> cachedThemes = themeCache.get(clazz);
        if (themes != null && cachedThemes == null) {
            cachedThemes = new ArrayList<>();
            for(Theme theme : themes) {
                // Check by widgets class name first
                WidgetTheme widgetTheme = theme.get(clazz.getName());
                if (widgetTheme != null) {
                    cachedThemes.add(widgetTheme);
                }

                // Check by widget class selector
                for (String styleName : object.getStyleName().split(" ")) {
                    WidgetTheme styleWidgetTheme = theme.get(styleName);
                    if (styleWidgetTheme != null) {
                        cachedThemes.add(styleWidgetTheme);
                    }
                }
            }
            themeCache.put(clazz, cachedThemes);
        }

        if (cachedThemes != null) {
            widgetThemes.addAll(cachedThemes);
        }


        // check the supertype
        if (clazz.getSuperclass() != null && !clazz.getSuperclass().equals(MaterialWidget.class)) {
            return getWidgetThemes(object, clazz.getSuperclass(), widgetThemes);
        } else {
            return widgetThemes;
        }
    }

    public static List<WidgetTheme> applyLoad(MaterialWidget object) {
        List<WidgetTheme> widgetThemes = getWidgetThemes(object);
        for (WidgetTheme widgetTheme : widgetThemes) {
            object = widgetTheme.onWidgetLoad(object);
        }
        return widgetThemes;
    }

    public static List<WidgetTheme> applyUnload(MaterialWidget object) {
        List<WidgetTheme> widgetThemes = getWidgetThemes(object);
        for (WidgetTheme widgetTheme : widgetThemes) {
            object = widgetTheme.onWidgetUnload(object);
        }
        return widgetThemes;
    }

    public static Theme getThemeByName(String name) {
        for (Theme theme : getThemes()) {
            if (theme.getName().equals(name)) {
                return theme;
            }
        }
        return null;
    }

    public static <T extends MaterialWidget> void addWidgetTheme(String themeName, WidgetTheme<T> widgetTheme) {
        Theme theme = getThemeByName(themeName);
        assert theme != null : "cannot find theme with name " + themeName;
        addWidgetTheme(theme, widgetTheme);
    }

    public static <T extends MaterialWidget> void addWidgetTheme(Theme theme, WidgetTheme<T> widgetTheme) {
        theme.put(widgetTheme.getClassSelector(), widgetTheme);
    }
}
