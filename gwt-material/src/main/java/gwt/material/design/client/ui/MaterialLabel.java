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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.mixin.TextMixin;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;

//@formatter:off

/**
 * Material Label will extend to GWT Label functionality with other material specifications.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code <m:MaterialLabel text="I love material design" />}
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="https://material.io/guidelines/style/typography.html">Material Design Specification</a>
 */
//@formatter:on
public class MaterialLabel extends AbstractValueWidget<String> implements HasText {

    private TextMixin<MaterialLabel> textMixin;

    public MaterialLabel() {
        super(Document.get().createSpanElement(), CssName.MATERIAL_LABEL);
    }

    public MaterialLabel(String text) {
        this();
        setText(text);
    }

    public MaterialLabel(String text, Color textColor) {
        this(text);
        setTextColor(textColor);
    }

    @Override
    public String getText() {
        return getValue();
    }

    @Override
    public void setText(String text) {
        setValue(text, true);
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        getTextMixin().setText(value);
        super.setValue(value, fireEvents);
    }

    @Override
    public String getValue() {
        return getTextMixin().getText();
    }

    protected TextMixin<MaterialLabel> getTextMixin() {
        if (textMixin == null) {
            textMixin = new TextMixin<>(this);
        }
        return textMixin;
    }
}
