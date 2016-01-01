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

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.constants.BrowserPrefixCssType;

/**
 * @author chriswjones
 */
public final class BrowserPrefixHelper {

    public static void updateStyleProperties(Element element, String ieProperty, String[] properties, BrowserPrefixCssType cssType) {
        updateStyleProperty(element, ieProperty, cssType.getIeValue());
        updateStyleProperties(element, properties, cssType.getValue());
    }

    public static void updateStyleProperties(Element element, String[] properties, BrowserPrefixCssType cssType) {
        updateStyleProperties(element, properties, cssType.getValue());
    }

    public static void updateStyleProperties(Element element, String[] properties, String value) {
        for (String p : properties) {
            updateStyleProperty(element, p, value);
        }
    }

    public static void updateStyleProperty(Element element, String property, String value) {
        if (element == null || property == null || property.isEmpty()) {
            return;
        }

        if (value == null || value.isEmpty()) {
            element.getStyle().clearProperty(property);
        } else {
            element.getStyle().setProperty(property, value);
        }
    }
}
