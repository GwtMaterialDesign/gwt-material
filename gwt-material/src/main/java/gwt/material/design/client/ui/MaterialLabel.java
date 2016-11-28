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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HasText;
import gwt.material.design.client.base.HasFontSize;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.FontSizeMixin;
import gwt.material.design.client.base.mixin.TextMixin;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.html.Span;

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
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#buttons">Material Link</a>
 */
//@formatter:on
public class MaterialLabel extends MaterialWidget implements HasFontSize, HasText {

    private final TextMixin<MaterialLabel> textMixin = new TextMixin<>(this);
    private final FontSizeMixin<MaterialLabel> fontSizeMixin = new FontSizeMixin<>(this);

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
    public void setFontSize(String fontSize) {
        fontSizeMixin.setFontSize(fontSize);
    }

    @Override
    public String getFontSize() {
        return fontSizeMixin.getFontSize();
    }

    @Override
    public void setFontSize(double fontSize, Unit unit) {
        fontSizeMixin.setFontSize(fontSize, unit);
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
