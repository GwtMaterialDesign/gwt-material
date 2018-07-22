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

import com.google.gwt.user.client.Window;
import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasShrinkableNavBarHandlers;
import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.events.NavBarExpandEvent;
import gwt.material.design.client.events.NavBarShrinkEvent;

import static gwt.material.design.jquery.client.api.JQuery.$;

//@formatter:off

/**
 * It's an extension to {@link MaterialNavBar} that provides
 * a delightful shrink capabilities when scrolling the page content.
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialNavBarShrink backgroundColor="BLUE" >
 *     <m:MaterialNavBrand href="#Test" position="LEFT"  text="Title" />
 *     <m:MaterialNavSection position="RIGHT">
 *         <m:MaterialLink  iconType="ACCOUNT_CIRCLE" iconPosition="LEFT" text="Account"  textColor="WHITE" waves="LIGHT"/>
 *         <m:MaterialLink  iconType="AUTORENEW" iconPosition="LEFT" text="Refresh" textColor="WHITE" waves="LIGHT"/>
 *         <m:MaterialLink  iconType="SEARCH" tooltip="Menu" textColor="WHITE" waves="LIGHT"/>
 *          <m:MaterialLink  iconType="MORE_VERT" tooltip="Starter" textColor="WHITE" waves="LIGHT"/>
 *     </m:MaterialNavSection>
 * </m:MaterialNavBar>
 * }
 * <pre>
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#navbar">Material Nav Bar</a>
 * @see <a href="https://material.io/guidelines/components/toolbars.html#">Material Design Specification</a>
 * @see <a href="https://gwtmaterialdesign.github.io/gwt-material-patterns/snapshot/#navbar_shrink">Pattern Shrink</a>
 */
//@formatter:on
public class MaterialNavBarShrink extends MaterialNavBar implements HasShrinkableNavBarHandlers {

    private int scrollOffset = 300;

    public MaterialNavBarShrink() {
        super();
        setInitialClasses(NavBarType.SHRINK.getCssName());
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        $("header").css("position", "fixed");
        $("header").css("width", "100%");
        final boolean[] fired = {false};
        registerHandler(Window.addWindowScrollHandler(scrollEvent -> {
            int distanceY = window().scrollTop();

            if (distanceY > scrollOffset) {
                $(getElement()).addClass("smaller");
                if (!fired[0]) {
                    NavBarShrinkEvent.fire(this);
                    fired[0] = true;
                }
            } else {
                if ($(getElement()).hasClass("smaller")) {
                    $(getElement()).removeClass("smaller");
                    NavBarExpandEvent.fire(this);
                    fired[0] = false;
                }
            }
        }));
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public void setScrollOffset(int scrollOffset) {
        this.scrollOffset = scrollOffset;
    }

    @Override
    public HandlerRegistration addExpandHandler(NavBarExpandEvent.NavBarExpandHandler handler) {
        return addHandler(handler, NavBarExpandEvent.TYPE);
    }

    @Override
    public HandlerRegistration addShrinkHandler(NavBarShrinkEvent.NavBarShrinkHandler handler) {
        return addHandler(handler, NavBarShrinkEvent.TYPE);
    }
}
