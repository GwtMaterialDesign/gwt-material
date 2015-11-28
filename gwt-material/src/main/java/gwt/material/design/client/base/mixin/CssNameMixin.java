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

import com.google.gwt.dom.client.Style.HasCssName;
import com.google.gwt.user.client.ui.UIObject;

/**
 * @author Ben Dol
 */
public class CssNameMixin<H extends UIObject, T extends HasCssName> extends StyleMixin<H> {

    private T style;

    public CssNameMixin(final H widget) {
        super(widget);
    }

    public void setCssName(T style) {
        this.style = style;
        setStyle(style.getCssName());
    }

    public T getCssName() {
        return style;
    }
}
