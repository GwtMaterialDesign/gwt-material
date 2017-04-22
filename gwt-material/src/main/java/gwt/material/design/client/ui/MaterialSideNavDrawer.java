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

import com.google.gwt.core.client.Scheduler;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasWithHeader;
import gwt.material.design.client.constants.SideNavType;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * SideNav (Drawer) is an extension to {@link MaterialSideNav} that provides
 * a drawer / overlay like structure. Good for Full Content view.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialSideNavDrawer ui:field="sideNav" width="280" withHeader="false" m:id="mysidebar" closeOnClick="false">
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
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#sidenav_drawer">Pattern</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#sidenav_drawer_header">Pattern with Header</a>
 */
//@formatter:on
public class MaterialSideNavDrawer extends MaterialSideNav implements HasWithHeader {

    private HandlerRegistration overlayOpeningHandler;
    private boolean withHeader;

    public MaterialSideNavDrawer() {
        super(SideNavType.DRAWER);
        setShowOnAttach(false);
    }

    @Override
    protected void build() {
        if (withHeader) {
            applyDrawerWithHeader();
        } else {
            appyDrawerType();
        }
    }

    @Override
    public void setWithHeader(boolean withHeader) {
        this.withHeader = withHeader;
    }

    @Override
    public boolean isWithHeader() {
        return withHeader;
    }

    /**
     * Provides an overlay / drawer sidenav just like when opening sidenav on mobile / tablet
     */
    protected void appyDrawerType() {
        setType(SideNavType.DRAWER);
        if (overlayOpeningHandler == null) {
            overlayOpeningHandler = addOpeningHandler(event -> Scheduler.get().scheduleDeferred(() -> $("[id=sidenav-overlay]").css("visibility", "visible")));
        }
        Scheduler.get().scheduleDeferred(() -> {
            pushElement(getHeader(), 0);
            pushElement(getMain(), 0);
        });
    }

    /**
     * Provides an overlay / drawer sidenav that will float on top of the content not the navbar without
     * any grey overlay behind it.
     */
    protected void applyDrawerWithHeader() {
        setType(SideNavType.DRAWER_WITH_HEADER);
        applyBodyScroll();

        if (isShowOnAttach()) {
            Scheduler.get().scheduleDeferred(() -> {
                pushElement(getHeader(), 0);
                pushElement(getMain(), 0);
            });
        }
    }
}
