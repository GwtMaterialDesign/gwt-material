package gwt.material.design.client.base.mixin;

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

import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Ben Dol
 */
public class ColorsMixin<T extends UIObject & HasColors> extends AbstractMixin<T> implements HasColors {

    private String bgColor = "";
    private String textColor = "";

    public ColorsMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setBackgroundColor(String bgColor) {
        if(this.bgColor != null && !this.bgColor.isEmpty()) {
            uiObject.removeStyleName(this.bgColor);
        }
        this.bgColor = bgColor;

        if(bgColor != null && !bgColor.isEmpty()) {
            uiObject.addStyleName(bgColor);
        }
    }

    @Override
    public String getBackgroundColor() {
        return bgColor;
    }

    @Override
    public void setTextColor(String textColor) {
        if(this.textColor != null && !this.textColor.isEmpty()) {
            uiObject.removeStyleName(this.textColor);
        }
        this.textColor = ensureTextColorFormat(textColor);

        if(this.textColor != null && !this.textColor.isEmpty()) {
            uiObject.addStyleName(this.textColor);
        }
    }

    @Override
    public String getTextColor() {
        return textColor;
    }

    /**
     * Allow for the use of text shading and auto formatting.
     */
    protected String ensureTextColorFormat(String textColor) {
        String formatted = "";
        boolean mainColor = true;
        for(String style : textColor.split(" ")) {
            if(mainColor) {
                // the main color
                if(!style.endsWith("-text")) {
                    style += "-text";
                }
                mainColor = false;
            } else {
                // the shading type
                if(!style.startsWith("text-")) {
                    style = " text-" + style;
                }
            }
            formatted += style;
        }
        return formatted;
    }
}
