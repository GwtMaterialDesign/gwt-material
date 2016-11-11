package gwt.material.design.client.base.helper;

/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2013 GwtBootstrap3
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

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.UIObject;

/**
 * Helper methods regarding CSS styling of UIObjects.
 *
 * @author Sven Jacobs
 * @author Joshua Godi
 */
public final class StyleHelper {

    public static <F extends Enum<? extends Style.HasCssName>> F fromStyleName(final Class<F> enumClass,
                                                                               final Style.HasCssName styleName) {
        return EnumHelper.fromStyleName(styleName.getCssName(), enumClass, null);
    }

    /**
     * Convenience method for first removing all enum style constants and then adding the single one.
     *
     * @see #removeEnumStyleNames(UIObject, Class)
     * @see #addEnumStyleName(UIObject, Style.HasCssName)
     */
    public static <E extends Style.HasCssName, F extends Enum<? extends Style.HasCssName>> void addUniqueEnumStyleName(final UIObject uiObject,
                                                                                                                       final Class<F> enumClass,
                                                                                                                       final E style) {
        removeEnumStyleNames(uiObject, enumClass);
        addEnumStyleName(uiObject, style);
    }

    /**
     * Removes all CSS style names specified by an enum that implements {@link Style.HasCssName} from an UIObject.
     *
     * @param uiObject  Object to remove CSS class names from
     * @param enumClass Enum representing CSS class names
     * @param <E>       Enum type implementing {@link Style.HasCssName}
     */
    public static <E extends Enum<? extends Style.HasCssName>> void removeEnumStyleNames(final UIObject uiObject,
                                                                                         final Class<E> enumClass) {

        for (final Enum<? extends Style.HasCssName> constant : enumClass.getEnumConstants()) {
            final String cssClass = ((Style.HasCssName) constant).getCssName();

            if (cssClass != null && !cssClass.isEmpty()) {
                uiObject.removeStyleName(cssClass);
            }
        }
    }

    /**
     * Adds enum value style name to UIObject unless style is {@code null}.
     *
     * @param uiObject Object to add style to
     * @param style    Style name
     */
    public static <E extends Style.HasCssName> void addEnumStyleName(final UIObject uiObject,
                                                                     final E style) {

        if (style != null && style.getCssName() != null && !style.getCssName().isEmpty()) {
            uiObject.addStyleName(style.getCssName());
        }
    }

    /**
     * Removes enum value style name from UIObject unless style is {@code null}.
     *
     * @param uiObject Object to remove style from
     * @param style    Style name
     */
    public static <E extends Style.HasCssName> void removeEnumStyleName(final UIObject uiObject,
                                                                        final E style) {

        if (style != null && style.getCssName() != null && !style.getCssName().isEmpty()) {
            uiObject.removeStyleName(style.getCssName());
        }
    }

    /**
     * Returns {@code true} if specified style is contained in space-separated list of styles
     *
     * @param styleNames Space-separated list of styles
     * @param style      Style to look for
     * @return True if contains style
     */
    public static boolean containsStyle(final String styleNames,
                                        final String style) {
        if (styleNames == null || style == null) {
            return false;
        }

        final String[] styles = styleNames.split("\\s");

        for (final String s : styles) {
            if (style.equals(s)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Toggles a style name on a ui object
     *
     * @param uiObject    Object to toggle style on
     * @param toggleStyle whether or not to toggle the style name on the object
     * @param styleName   Style name
     */
    public static void toggleStyleName(final UIObject uiObject,
                                       final boolean toggleStyle,
                                       final String styleName) {
        if (toggleStyle) {
            uiObject.addStyleName(styleName);
        } else {
            uiObject.removeStyleName(styleName);
        }
    }

    public static Double getMeasurementValue(String value) {
        if (value == null) {
            return null;
        }
        try {
            return Double.parseDouble(value.replaceAll("[^0-9.]", ""));
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Style.Unit getMeasurementUnit(String value) {
        if (value == null) {
            return null;
        }
        try {
            return Style.Unit.valueOf(
                    value.replaceAll("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?", "").toUpperCase());
        } catch (IllegalArgumentException e) {
            // Silently catch invalid units
            return null;
        }
    }

    private StyleHelper() {
    }
}
