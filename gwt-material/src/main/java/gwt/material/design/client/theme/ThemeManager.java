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
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@SuppressWarnings("unchecked")
public class ThemeManager {

    private static SortedSet<Theme> themes;

    public static void addTheme(Theme theme) {
        if (!themes.contains(theme)) {
            themes.add(theme);

            for(WidgetTheme widgetTheme : theme.apply()) {
                widgetTheme.setTheme(theme);
                addWidgetTheme(theme.getName(), widgetTheme);
            }
        } else {
            GWT.log("Theme '" + theme.getName() + "' has already been loaded (ignoring '"+theme.getClass().getSimpleName()+"').");
        }
    }

    public static SortedSet<Theme> getThemes() {
        if (themes == null) {
            themes = new TreeSet<>();
        }
        return themes;
    }

    public static <T extends MaterialWidget> List<WidgetTheme<T>> getWidgetThemes(T object) {
        List<WidgetTheme<T>> widgetThemes = new ArrayList<>();
        if (themes != null) {
            for(Theme theme : themes) {
                // Check by widgets class name first
                WidgetTheme widgetTheme = theme.get(object.getClass().getName());
                if (widgetTheme != null) {
                    widgetThemes.add(widgetTheme);
                }

                // Check by widget id selector
                WidgetTheme idWidgetTheme = theme.get(object.getId());
                if (idWidgetTheme != null) {
                    widgetThemes.add(idWidgetTheme);
                }
            }
        }
        return widgetThemes;
    }

    public static <T extends MaterialWidget> List<WidgetTheme<T>> applyLoad(T object) {
        // TODO: Other onWidgetLoad theme tasks
        List<WidgetTheme<T>> widgetThemes = getWidgetThemes(object);
        for (WidgetTheme<T> widgetTheme : widgetThemes) {
            object = widgetTheme.onWidgetLoad(object);
        }
        return widgetThemes;
    }

    public static <T extends MaterialWidget> List<WidgetTheme<T>> applyUnload(T object) {
        // TODO: Other onWidgetUnload theme tasks
        List<WidgetTheme<T>> widgetThemes = getWidgetThemes(object);
        for (WidgetTheme<T> widgetTheme : widgetThemes) {
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
        theme.put(widgetTheme.getIdSelector(), widgetTheme);
    }
}
