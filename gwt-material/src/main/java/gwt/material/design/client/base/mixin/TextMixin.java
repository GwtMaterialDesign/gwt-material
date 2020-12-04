package gwt.material.design.client.base.mixin;

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

import com.google.gwt.safehtml.shared.HtmlSanitizer;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.DefaultHtmlSanitizer;
import gwt.material.design.client.base.HasSafeText;

/**
 * @author Mark Kevin
 * @author Ben Dol
 */
public class TextMixin<T extends UIObject & HasSafeText> extends AbstractMixin<T> implements HasSafeText {

    public static HtmlSanitizer DEFAULT_SANITIZER = new DefaultHtmlSanitizer();
    protected HtmlSanitizer _sanitizer;

    public TextMixin(final T uiObject) {
        super(uiObject);
    }

    public String getText() {
        return uiObject.getElement().getInnerText();
    }

    public void setText(final String text) {
        setText(toSafeHtml(text));
    }

    protected SafeHtml toSafeHtml(String text) {
        SafeHtml safeHtml;
        if (_sanitizer == null) {
            safeHtml = DEFAULT_SANITIZER.sanitize(text);
        } else {
            safeHtml = _sanitizer.sanitize(text);
        }
        return safeHtml;
    }

    @Override
    public void setText(SafeHtml html) {
        uiObject.getElement().setInnerSafeHtml(html);
    }

    public static void setDefaultSanitizer(HtmlSanitizer defaultSanitizer) {
        DEFAULT_SANITIZER = defaultSanitizer;
    }

    @Override
    public void setSanitizer(HtmlSanitizer sanitizer) {
        _sanitizer = sanitizer;
    }

    @Override
    public HtmlSanitizer getSanitizer() {
        return _sanitizer;
    }
}
