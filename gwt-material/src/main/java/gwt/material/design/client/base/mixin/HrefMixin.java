/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
import gwt.material.design.client.base.HasHref;

public class HrefMixin<T extends UIObject & HasHref> implements HasHref {

    private T widget;

    public HrefMixin(T widget) {
        this.widget = widget;
    }

    @Override
    public void setHref(String href) {
        if  (widget != null) {
            if (href != null) {
                widget.getElement().setAttribute("href", href);
            } else {
                widget.getElement().removeAttribute("href");
            }
        }
    }

    @Override
    public String getHref() {
        return widget.getElement().getAttribute("href");
    }

    @Override
    public void setTarget(String target) {
        if (widget != null) {
            if (target != null) {
                widget.getElement().setAttribute("target", target);
            } else {
                widget.getElement().removeAttribute("target");
            }
        }
    }

    @Override
    public String getTarget() {
        return widget.getElement().getAttribute("target");
    }
}
