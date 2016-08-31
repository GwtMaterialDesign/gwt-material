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
package gwt.material.design.client.base.mixin;

import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasFontSize;
import gwt.material.design.client.base.helper.StyleHelper;

/**
 * @author Ben Dol
 */
public class FontSizeMixin<T extends Widget & HasFontSize> extends AbstractMixin<T> implements HasFontSize {

    public FontSizeMixin(final T widget) {
        super(widget);
    }

    public void setFontSize(String fontSize) {
        double value = StyleHelper.getMeasurementValue(fontSize);
        Style.Unit unit = StyleHelper.getMeasurementUnit(fontSize);

        assert unit != null : "Font size did not contain a unit.";
        setFontSize(value, unit);
    }

    public String getFontSize() {
        return uiObject.getElement().getStyle().getFontSize();
    }

    public void setFontSize(double fontSize, Style.Unit unit) {
        uiObject.getElement().getStyle().setFontSize(fontSize, unit);
    }
}
