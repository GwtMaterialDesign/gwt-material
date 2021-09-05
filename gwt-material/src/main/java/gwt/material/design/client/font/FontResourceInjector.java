/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2021 GwtMaterialDesign
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
package gwt.material.design.client.font;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LinkElement;
import com.google.gwt.dom.client.StyleElement;
import gwt.material.design.client.base.DefaultHtmlSanitizer;

public class FontResourceInjector {

    private static Element headElement;
    private static Font defaultFont;

    public static void inject(Font font) {
        injectGoogleFontLink();

        String name = getDefaultFont().getName();
        if (createFontUrlLink(font.getResourceUrl())) {
            name = font.getName();
        }

        // Will generate a style tag containing the font name
        StyleElement styleElement = Document.get().createStyleElement();
        styleElement.setInnerSafeHtml(new DefaultHtmlSanitizer().sanitize("body * { font-family:" + name + ";}"));
        getHeadElement().appendChild(styleElement);
    }

    public static Font getDefaultFont() {
        if (defaultFont == null) {
            defaultFont = new OpenSansFont();
        }
        return defaultFont;
    }

    public static void setDefaultFont(Font defaultFont) {
        FontResourceInjector.defaultFont = defaultFont;
    }

    protected static void injectGoogleFontLink() {
        LinkElement fontGoogleApisLink = Document.get().createLinkElement();
        fontGoogleApisLink.setRel("preconnect");
        fontGoogleApisLink.setHref("https://fonts.googleapis.com");
        getHeadElement().appendChild(fontGoogleApisLink);

        LinkElement gstaticLink = Document.get().createLinkElement();
        gstaticLink.setRel("preconnect");
        gstaticLink.setHref("https://fonts.gstatic.com");
        gstaticLink.setAttribute("crossorigin", "");
        getHeadElement().appendChild(gstaticLink);
    }

    protected static boolean createFontUrlLink(String resourceUrl) {
        if (resourceUrl != null) {
            LinkElement fontUrlLink = Document.get().createLinkElement();
            fontUrlLink.setHref(resourceUrl);
            fontUrlLink.setRel("stylesheet");
            getHeadElement().appendChild(fontUrlLink);
            return true;
        }
        return false;
    }

    public static Element getHeadElement() {
        if (headElement == null) {
            headElement = Document.get().getElementsByTagName("head").getItem(0);
        }
        return headElement;
    }
}
