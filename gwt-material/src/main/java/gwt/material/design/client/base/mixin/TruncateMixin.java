/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2022 GwtMaterialDesign
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

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasTruncate;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.CssName;

public class TruncateMixin<T extends MaterialWidget & HasTruncate> extends AbstractMixin<T> implements HasTruncate {

    private ToggleStyleMixin<MaterialWidget> toggleStyleMixin;

    public TruncateMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setTruncate(boolean truncate) {
        getToggleStyleMixin().setOn(truncate);
        checkEllipsis();
    }

    public void checkEllipsis() {
        if (uiObject instanceof HasText) {
            Element element = uiObject.getElement();
            HasText hasText = (HasText) uiObject;
            element.setAttribute("title", "");
            uiObject.addMouseOverHandler(event -> {
                if (!uiObject.getElement().hasAttribute("title")) return;
                String text = hasText.getText();
                boolean withEllipsis = element.getOffsetWidth() < element.getScrollWidth();
                if (withEllipsis) {
                    element.setAttribute("title", text);
                }
            });

            uiObject.addMouseOutHandler(event -> element.setAttribute("title", ""));
        }
    }

    @Override
    public boolean isTruncate() {
        return getToggleStyleMixin().isOn();
    }

    public ToggleStyleMixin<MaterialWidget> getToggleStyleMixin() {
        if (toggleStyleMixin == null) {
            toggleStyleMixin = new ToggleStyleMixin<>(uiObject, CssName.TRUNCATE);
        }
        return toggleStyleMixin;
    }
}
