/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
package gwt.material.design.client.ui;

import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.SideNavType;

import static gwt.material.design.client.js.JsMaterialElement.$;

public class MaterialMiniSideNav extends MaterialSideNav {

    private boolean expandable;
    private boolean expandOnClick;
    private HandlerRegistration miniWithOpeningExpandHandler;
    private HandlerRegistration miniWithClosingExpandHandler;

    public MaterialMiniSideNav() {
        super(SideNavType.MINI);
    }

    @Override
    protected void build() {
        applyBodyScroll();
        if (isExpandable()) {
            setType(SideNavType.MINI_WITH_EXPAND);
            applyTransition(getMain());
            applyTransition(getFooter());

            int originalWidth = getWidth();
            int miniWidth = 64;
            pushElement(getMain(), miniWidth);
            pushElementMargin(getFooter(), miniWidth);
            setShowOnAttach(false);
            setWidth(miniWidth);

            if (miniWithOpeningExpandHandler == null) {
                miniWithOpeningExpandHandler = addOpeningHandler(event -> expand(originalWidth));
            }

            if (miniWithClosingExpandHandler == null) {
                miniWithClosingExpandHandler = addClosingHandler(event -> collapse(miniWidth));
            }

            // Add Opening when sidenav link is clicked by default
            for (Widget w : getChildren()) {
                if (w instanceof MaterialWidget && isExpandOnClick()) {
                    $(w.getElement()).off("click").on("click", (e, param1) -> {
                        if (!getElement().hasClassName("expanded")) {
                            show();
                        }
                        return true;
                    });
                }
            }
        } else {
            setType(SideNavType.MINI);
            setWidth(64);
        }
    }

    protected void expand(int width) {
        addStyleName("expanded");
        setWidth(width);
        pushElement(getMain(), width);
        pushElementMargin(getFooter(), width);
    }

    protected void collapse(int width) {
        removeStyleName("expanded");
        setWidth(width);
        pushElement(getMain(), width);
        pushElementMargin(getFooter(), width);
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public boolean isExpandable() {
        return expandable;
    }

    public boolean isExpandOnClick() {
        return expandOnClick;
    }

    public void setExpandOnClick(boolean expandOnClick) {
        this.expandOnClick = expandOnClick;
    }
}
