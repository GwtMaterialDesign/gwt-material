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
import gwt.material.design.jquery.client.api.JQuery;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * Push SideNav is an extension to {@link MaterialSideNav} that pushes
 * the {@link MaterialContainer}, {@link MaterialHeader}, and {@link MaterialFooter} when
 * opening and closing the sidenav.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialPushSideNav ui:field="sideNav" width="280" withHeader="false" m:id="mysidebar" closeOnClick="false">
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
 */
//@formatter:on
public class MaterialPushSideNav extends MaterialSideNav implements HasWithHeader {

    private HandlerRegistration pushWithHeaderOpeningHandler;
    private HandlerRegistration pushWithHeaderClosingHandler;
    private boolean withHeader;

    public MaterialPushSideNav() {
        super(SideNavType.PUSH);
        setShowOnAttach(true);
    }

    @Override
    protected void build() {
        if (withHeader) {
            applyPushWithHeader();
        } else {
            applyPushType();
        }
    }

    @Override
    public void setWithHeader(boolean withHeader) {
        this.withHeader = withHeader;
    }

    /**
     * Push the header, footer, and main to the right part when Close type is applied.
     */
    protected void applyPushType() {
        setType(SideNavType.PUSH);
        $(JQuery.window()).off("resize").resize((e, param1) -> {
            if (!isAlwaysShowActivator() && !isOpen() && gwt.material.design.client.js.Window.matchMedia("all and (min-width: 992px)")) {
                show();
            }
            pushElements(isOpen(), getWidth());
            return true;
        });
    }

    protected void applyPushWithHeader() {
        setType(SideNavType.PUSH_WITH_HEADER);
        applyTransition(getMain());
        applyTransition(getFooter());
        applyBodyScroll();
        if (isShowOnAttach()) {
            Scheduler.get().scheduleDeferred(() -> {
                pushElement(getHeader(), 0);
                pushElement(getMain(), getWidth());
                pushElementMargin(getFooter(), getWidth());
            });
        }

        if (pushWithHeaderOpeningHandler == null) {
            pushWithHeaderOpeningHandler = addOpeningHandler(event -> {
                pushElement(getMain(), getWidth());
                pushElementMargin(getFooter(), getWidth());
            });
        }

        if (pushWithHeaderClosingHandler == null) {
            pushWithHeaderClosingHandler = addClosingHandler(event -> {
                pushElement(getMain(), 0);
                pushElementMargin(getFooter(), 0);
            });
        }
    }

    @Override
    protected void onClosing() {
        super.onClosing();
        if (!withHeader) {
            pushElements(false, getWidth());
        }
    }

    @Override
    protected void onOpening() {
        super.onOpening();
        if (!withHeader) {
            pushElements(true, getWidth());
        }
    }
}
