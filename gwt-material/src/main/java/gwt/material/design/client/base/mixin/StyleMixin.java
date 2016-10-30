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

/**
 * @author Ben Dol
 */
public class StyleMixin<H extends UIObject> extends AbstractMixin<H> {

    private String style;

    public StyleMixin(final H widget) {
        super(widget);
    }

    public void setStyle(String style) {
        if (this.style != null && !this.style.isEmpty()) {
            uiObject.removeStyleName(this.style);
        }
        this.style = style;

        if (style != null && !style.isEmpty()) {
            uiObject.addStyleName(style);
        }
    }

    public String getStyle() {
        return style;
    }
}
