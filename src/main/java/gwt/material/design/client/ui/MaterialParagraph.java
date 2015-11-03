package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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


import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHTML;
import gwt.material.design.client.base.mixin.HTMLMixin;

public class MaterialParagraph extends HTMLPanel implements HasHTML {
    private final HTMLMixin<MaterialParagraph> htmlMixin = new HTMLMixin<>(this);

    public MaterialParagraph() {
        this("");
    }

    public MaterialParagraph(final String html) {
        super("p", html);
    }

    @Override
    public void setText(final String text) {
        htmlMixin.setText(text);
    }

    @Override
    public String getText() {
        return htmlMixin.getText();
    }

    @Override
    public String getHTML() {
        return htmlMixin.getHTML();
    }

    @Override
    public void setHTML(final String html) {
        htmlMixin.setHTML(html);
    }
}
