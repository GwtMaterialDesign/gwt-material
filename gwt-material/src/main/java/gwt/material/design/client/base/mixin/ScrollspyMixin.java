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
import gwt.material.design.client.base.HasScrollspy;
import gwt.material.design.client.base.helper.StyleHelper;
import gwt.material.design.client.constants.CssName;

/**
 * @author Ben Dol
 */
public class ScrollspyMixin<T extends UIObject & HasScrollspy> extends AbstractMixin<T> implements HasScrollspy {

    public ScrollspyMixin(final T uiObject) {
        super(uiObject);
    }

    @Override
    public void setScrollspy(String scrollspy) {
        uiObject.removeStyleName(CssName.SECTION + " " + CssName.SCROLLSPY);

        if (scrollspy != null) {
            uiObject.addStyleName(CssName.SECTION + " " + CssName.SCROLLSPY);
            setId(scrollspy);
        }
    }

    @Override
    public String getScrollspy() {
        if (StyleHelper.containsStyle(uiObject.getStyleName(), CssName.SCROLLSPY)) {
            return getId();
        } else {
            return null;
        }
    }

    @Override
    public void setId(String id) {
        uiObject.setId(id);
    }

    @Override
    public String getId() {
        return uiObject.getId();
    }
}
