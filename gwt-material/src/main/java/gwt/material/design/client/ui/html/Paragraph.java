package gwt.material.design.client.ui.html;

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

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.MaterialWidget;

public class Paragraph extends MaterialWidget implements HasText {

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

    /**
     * @return the text
     */
    @Override
    public String getText() {
        return getElement().getInnerText();
    }

    /**
     * @param text the text to set
     */
    @Override
    public void setText(String text) {
        getElement().setInnerText(text);
    }
}