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
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.DefaultHtmlSanitizer;
import gwt.material.design.client.base.HasSanitizedText;

/**
 * @author Ben Dol
 */
public class TextMixin<T extends UIObject & HasSanitizedText> extends AbstractMixin<T> implements HasSanitizedText {

    public TextMixin(final T uiObject) {
        super(uiObject);
    }

    public String getText() {
        return SafeHtmlUtils.fromString(uiObject.getElement().getInnerText()).asString();
    }

    public void setText(final String text) {
        setText(text, new DefaultHtmlSanitizer());
    }

    @Override
    public void setText(String text, HtmlSanitizer sanitizer) {
        if (sanitizer == null) {
            sanitizer = new DefaultHtmlSanitizer();
        }
        uiObject.getElement().setInnerSafeHtml(sanitizer.sanitize(text));
    }
}
