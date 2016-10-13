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

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasColors;
import gwt.material.design.client.constants.Color;

/**
 * @author kevzlou7979
 * @author Ben Dol
 */
public class ColorsMixin<T extends UIObject & HasColors> extends AbstractMixin<T> implements HasColors {

    private Color bgColor;
    private Color textColor;

    public ColorsMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setBackgroundColor(Color bgColor) {
        if (this.bgColor != null && !this.bgColor.getCssName().isEmpty()) {
            uiObject.removeStyleName(this.bgColor.getCssName());
        }
        this.bgColor = bgColor;

        if (bgColor != null && !bgColor.getCssName().isEmpty()) {
            uiObject.addStyleName(bgColor.getCssName());
        }
    }

    @Override
    public Color getBackgroundColor() {
        return bgColor;
    }

    @Override
    public void setTextColor(Color textColor) {
        if (this.textColor != null && !this.textColor.getCssName().isEmpty()) {
            uiObject.removeStyleName(this.textColor.getCssName());
        }
        this.textColor = textColor;

        if (this.textColor != null && !this.textColor.getCssName().isEmpty()) {
            uiObject.addStyleName(textColor.getCssName());
        }
    }

    @Override
    public Color getTextColor() {
        return textColor;
    }
}
