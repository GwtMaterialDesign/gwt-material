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

//@formatter:off

/**
 * SideNav (Mini) is an extension to {@link MaterialSideNav} that provides
 * mini variant / icon only sidenav. Also with this type, you can enable
 * expansion feature by setExpandable(true).
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialSideNavMini ui:field="sideNav" width="280" m:id="mysidebar" expandable="true" expandOnClick="true">
 *     <m:MaterialLink href="#about" iconPosition="LEFT" iconType="OUTLINE" text="About" textColor="BLUE"  />
 *     <m:MaterialLink href="#gettingStarted" iconPosition="LEFT" iconType="DOWNLOAD" text="Getting Started" textColor="BLUE"  >
 * </m:MaterialSideNav>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#sidenavs">Material SideNav</a>
 * @see <a href="https://material.io/guidelines/patterns/navigation-drawer.html">Material Design Specification</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#sidenav_mini">Pattern</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#sidenav_mini_expandable">Pattern Expandable</a>
 */
//@formatter:on
public class MaterialSideNavMini extends MaterialSideNav {

    private boolean expandable;
    private boolean expandOnClick;
    private HandlerRegistration miniWithOpeningExpandHandler;
    private HandlerRegistration miniWithClosingExpandHandler;

    public MaterialSideNavMini() {
        super(SideNavType.MINI);
        setShowOnAttach(false);
    }

    @Override
    protected void build() {
        applyBodyScroll();
        if (isExpandable()) {
            setType(SideNavType.MINI_WITH_EXPAND);
            applyTransition(getElement());
            int originalWidth = getWidth();
            int miniWidth = 64;
            pushElement(getMain(), miniWidth);
            pushElementMargin(getFooter(), miniWidth);
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
