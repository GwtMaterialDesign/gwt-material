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
import gwt.material.design.client.base.AbstractSideNav;
import gwt.material.design.client.base.HasWithHeader;
import gwt.material.design.client.constants.SideNavType;
import gwt.material.design.client.events.SideNavPushEvent;
import gwt.material.design.jquery.client.api.JQuery;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * SideNav (Push) is an extension to {@link MaterialSideNav} that pushes
 * the {@link MaterialContainer}, {@link MaterialHeader}, and {@link MaterialFooter} when
 * opening and closing the sidenav.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialSideNavPush ui:field="sideNav" width="280" withHeader="false" m:id="mysidebar" closeOnClick="false">
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
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#sidenav_push">Pattern</a>
 */
//@formatter:on
public class MaterialSideNavPush extends AbstractSideNav implements HasWithHeader {

    private boolean withHeader;

    public MaterialSideNavPush() {
        super(SideNavType.PUSH);

        setAutoHideOnResize(true);
        setShowOnAttach(true);
    }

    @Override
    protected void setup() {
        if (withHeader) {
            applyPushWithHeader();
        } else {
            applyPushType();
        }
    }

    /**
     * Push the header, footer, and main to the right part when Close type is applied.
     */
    protected void applyPushType() {
        applyBodyScroll();
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
        super.applyBodyScroll();
        if (isShowOnAttach()) {
            Scheduler.get().scheduleDeferred(() -> {
                pushElement(getHeader(), 0);
                pushElement(getMain(), getWidth());
                pushElementMargin(getFooter(), getWidth());
            });
        }

        registerHandler(addOpeningHandler(event -> {
            pushElement(getMain(), getWidth());
            pushElementMargin(getFooter(), getWidth());
        }));

        registerHandler(addClosingHandler(event -> {
            pushElement(getMain(), 0);
            pushElementMargin(getFooter(), 0);
        }));
    }

    @Override
    protected void applyBodyScroll() {
        $("header").css("width", "100%");
        $("header").css("position", "fixed");
        $("header").css("zIndex", "997");
        $("header").css("top", "0");
        registerHandler(addOpeningHandler(event -> $("header").css("width", "calc(100% - " + getWidth() + "px)")));
        registerHandler(addClosingHandler(event -> $("header").css("width", "calc(100%)")));
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

    protected void pushElements(boolean toggle, int width) {
        int w = 0;
        if (!gwt.material.design.client.js.Window.matchMedia("all and (max-width: 992px)")) {
            if (toggle) {
                w = width;
            }

            applyTransition(getHeader());
            pushElementMargin(getHeader(), w);

            applyTransition(getMain());
            pushElementMargin(getMain(), w);

            applyTransition(getFooter());
            pushElementMargin(getFooter(), w);
        }
        onPush(toggle, w);
    }

    protected void onPush(boolean toggle, int width) {
        SideNavPushEvent.fire(this, getElement(), getActivator(), toggle, width);
    }

    @Override
    public void setWithHeader(boolean withHeader) {
        this.withHeader = withHeader;
    }

    @Override
    public boolean isWithHeader() {
        return withHeader;
    }
}
