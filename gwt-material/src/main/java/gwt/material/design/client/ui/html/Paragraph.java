/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.TextMixin;

public class Paragraph extends MaterialWidget implements HasText {

    private final TextMixin<Paragraph> textMixin = new TextMixin<>(this);

    public Paragraph() {
        super(Document.get().createElement("p"));
    }

    public Paragraph(Widget item) {
        this();
        add(item);
    }

    public Paragraph(String text) {
        this();
        setText(text);
    }

    @Override
    public String getText() {
        return textMixin.getText();
    }

    @Override
    public void setText(String text) {
        textMixin.setText(text);
    }
}