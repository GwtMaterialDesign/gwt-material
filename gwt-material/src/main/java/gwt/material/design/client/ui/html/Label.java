/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
package gwt.material.design.client.ui.html;

import com.google.gwt.safehtml.shared.HtmlSanitizer;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.DOM;
import gwt.material.design.client.base.HasSafeText;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.TextMixin;

public class Label extends MaterialWidget implements HasSafeText {

    private TextMixin<Label> textMixin;

    public Label() {
        super(DOM.createLabel());
    }

    public Label(String text) {
        this();
        setText(text);
    }

    public String getText() {
        return getTextMixin().getText();
    }

    public void setText(String text) {
        getTextMixin().setText(text);
    }

    @Override
    public void setHtml(SafeHtml html) {
        getTextMixin().setHtml(html);
    }

    @Override
    public void setSanitizer(HtmlSanitizer sanitizer) {
        getTextMixin().setSanitizer(sanitizer);
    }

    @Override
    public HtmlSanitizer getSanitizer() {
        return getTextMixin().getSanitizer();
    }

    protected TextMixin<Label> getTextMixin() {
        if (textMixin == null) {
            textMixin = new TextMixin<>(this);
        }
        return textMixin;
    }
}
