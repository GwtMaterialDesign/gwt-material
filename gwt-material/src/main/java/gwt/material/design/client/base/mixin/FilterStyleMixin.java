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
import gwt.material.design.client.base.HasFilterStyle;
import gwt.material.design.client.base.HasFlexbox;
import gwt.material.design.client.base.helper.BrowserPrefixHelper;
import gwt.material.design.client.constants.*;

/**
 * Mixin for Filter layout
 *
 * @author kevzlou7979
 */
public class FilterStyleMixin<T extends Widget & HasFilterStyle> extends AbstractMixin<T> implements HasFilterStyle  {

    private String property;

    public FilterStyleMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setFilterStyle(String property) {
        this.property = property;
        BrowserPrefixHelper.updateStyleProperties(uiObject.getElement(),
                new String[]{"MsFilter", "WebkitFilter", "MozFilter", "filter"},
                property != null ? property : null);
    }

    @Override
    public String getFilterStyle() {
        return property;
    }
}
