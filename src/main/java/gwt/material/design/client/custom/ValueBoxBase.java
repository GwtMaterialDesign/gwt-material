package gwt.material.design.client.custom;

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

import com.google.gwt.dom.client.Element;
import com.google.gwt.text.shared.Parser;
import com.google.gwt.text.shared.Renderer;
import gwt.material.design.client.custom.mixin.ColorsMixin;
import gwt.material.design.client.custom.mixin.GridMixin;
import gwt.material.design.client.custom.mixin.IdMixin;

public class ValueBoxBase<T> extends com.google.gwt.user.client.ui.ValueBoxBase<T> implements HasId, HasColors,
        HasGrid, HasPlaceholder {

    private final IdMixin<ValueBoxBase<T>> idMixin = new IdMixin<>(this);
    private final ColorsMixin<ValueBoxBase<T>> colorsMixin = new ColorsMixin<>(this);
    private final GridMixin<ValueBoxBase<T>> gridMixin = new GridMixin<>(this);

    public ValueBoxBase(Element elem, Renderer<T> renderer, Parser<T> parser) {
        super(elem, renderer, parser);
    }

    @Override
    public void setId(String id) {
        idMixin.setId(id);
    }

    @Override
    public String getId() {
        return idMixin.getId();
    }

    @Override
    public void setBackgroundColor(String bgColor) {
        colorsMixin.setBackgroundColor(bgColor);
    }

    @Override
    public String getBackgroundColor() {
        return colorsMixin.getBackgroundColor();
    }

    @Override
    public void setTextColor(String textColor) {
        colorsMixin.setTextColor(textColor);
    }

    @Override
    public String getTextColor() {
        return colorsMixin.getTextColor();
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
    public void setPlaceholder(final String placeHolder) {
        getElement().setAttribute("placeholder", placeHolder != null ? placeHolder : "");
    }

    @Override
    public String getPlaceholder() {
        return getElement().getAttribute("placeholder");
    }
}
