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

/**
 * @author Sven Jacobs
 */
public final class EnumHelper {

    /**
     * Returns first enum constant found in at space-separated list of style names.
     *
     * @param styleName    Space-separated list of styles
     * @param enumClass    Type of enum
     * @param defaultValue Default value of no match was found
     * @return First enum constant found or default value
     */
    public static <E extends Enum<? extends Style.HasCssName>> E fromStyleName(final String styleName,
                                                                               final Class<E> enumClass,
                                                                               final E defaultValue) {
        return EnumHelper.fromStyleName(styleName, enumClass, defaultValue, false);
    }

    /**
     * Returns first enum constant found in at space-separated list of style names.
     *
     * @param styleName    Space-separated list of styles
     * @param enumClass    Type of enum
     * @param defaultValue Default value of no match was found
     * @return First enum constant found or default value
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<? extends Style.HasCssName>> E fromStyleName(final String styleName,
                                                                               final Class<E> enumClass,
                                                                               final E defaultValue,
                                                                               final boolean ignoreSpaces) {
        if (styleName == null || enumClass == null) {
            return defaultValue;
        }

        for (final Enum<? extends Style.HasCssName> constant : enumClass.getEnumConstants()) {
            final Style.HasCssName anEnum = (Style.HasCssName) constant;
            final String cssClass = anEnum.getCssName();

            if(cssClass != null) {
                boolean contains;
                if (ignoreSpaces) {
                    contains = styleName.equals(cssClass);
                } else {
                    contains = StyleHelper.containsStyle(styleName, cssClass);
                }
                if (contains) {
                    return (E) anEnum;
                }
            }
        }
        return defaultValue;
    }

    private EnumHelper() {
    }
}
