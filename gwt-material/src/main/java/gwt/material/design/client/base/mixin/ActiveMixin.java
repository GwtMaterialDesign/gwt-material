package gwt.material.design.client.base.mixin;

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


import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasActive;

public class ActiveMixin<T extends UIObject & HasActive> extends AbstractMixin<T> implements HasActive {

    public ActiveMixin(final T widget) {
        super(widget);
    }

    private boolean active;

    @Override
    public void setActive(boolean active) {
        this.active = active;
        if(active) {
            uiObject.removeStyleName("inactive");
            uiObject.addStyleName("active");
        } else {
            uiObject.removeStyleName("active");
            uiObject.addStyleName("inactive");
        }
    }

    @Override
    public boolean isActive() {
        return active;
    }
}
