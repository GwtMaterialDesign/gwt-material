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

import gwt.material.design.client.base.HasColors;
import gwt.material.design.client.base.HasFontSize;
import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.base.HasSeparator;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.user.client.ui.Label;
import gwt.material.design.client.base.mixin.ColorsMixin;
import gwt.material.design.client.base.mixin.FontSizeMixin;
import gwt.material.design.client.base.mixin.GridMixin;
import gwt.material.design.client.base.mixin.SeparatorMixin;

//@formatter:off
/**
 * Material Label will extend to GWT Label functionality with other material specifications
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code<m:MaterialLabel text="I love material design" />}
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#buttons">Material Link</a>
 */
//@formatter:on
public class MaterialLabel extends Label implements HasGrid, HasSeparator, HasColors, HasFontSize {

    private final ColorsMixin<MaterialLabel> colorsMixin = new ColorsMixin<>(this);
    private final GridMixin<MaterialLabel> gridMixin = new GridMixin<>(this);
    private final SeparatorMixin<MaterialLabel> separatorMixin = new SeparatorMixin<>(this);
    private final FontSizeMixin<MaterialLabel> fontSizeMixin = new FontSizeMixin<>(this);

    public MaterialLabel() {
    }

    public MaterialLabel(Element element) {
        super(element);
    }

    public MaterialLabel(String text, boolean wordWrap) {
        super(text, wordWrap);
    }

    public MaterialLabel(String text, Direction dir) {
        super(text, dir);
    }

    public MaterialLabel(String text, DirectionEstimator directionEstimator) {
        super(text, directionEstimator);
    }

    public MaterialLabel(String text) {
        super(text);
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
    public void setSeparator(boolean separator) {
        separatorMixin.setSeparator(separator);
    }

    @Override
    public boolean isSeparator() {
        return separatorMixin.isSeparator();
    }

    @Override
    public void setGrid(String grid) {
        gridMixin.setGrid(grid);
    }

    @Override
    public void setOffset(String offset) {
        gridMixin.setOffset(offset);
    }

    @Override
    public String getBackgroundColor() {
        return colorsMixin.getBackgroundColor();
    }

    @Override
    public void setBackgroundColor(String bgColor) {
        colorsMixin.setBackgroundColor(bgColor);
    }

    @Override
    public String getTextColor() {
        return colorsMixin.getTextColor();
    }

    @Override
    public void setTextColor(String textColor) {
        colorsMixin.setTextColor(textColor);
    }
}
