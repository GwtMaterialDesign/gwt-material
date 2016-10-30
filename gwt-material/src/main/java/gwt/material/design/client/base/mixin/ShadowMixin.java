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
import gwt.material.design.client.base.HasShadow;

/**
 * @author Ben Dol
 */
public class ShadowMixin<T extends UIObject & HasShadow> extends AbstractMixin<T> implements HasShadow {

    private int shadow;

    public ShadowMixin(final T uiObject) {
        super(uiObject);
    }

    @Override
    public void setShadow(int shadow) {
        uiObject.removeStyleName("z-depth-" + this.shadow);
        this.shadow = shadow;

        if (shadow >= 0) {
            uiObject.addStyleName("z-depth-" + shadow);
        }
    }

    @Override
    public int getShadow() {
        return shadow;
    }
}
