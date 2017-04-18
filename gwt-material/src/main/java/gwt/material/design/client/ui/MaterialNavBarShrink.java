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

import com.google.web.bindery.event.shared.HandlerRegistration;
import gwt.material.design.client.base.HasShrinkableNavBarHandlers;
import gwt.material.design.client.constants.NavBarType;
import gwt.material.design.client.events.NavBarExpandEvent;
import gwt.material.design.client.events.NavBarShrinkEvent;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class MaterialNavBarShrink extends MaterialNavBar implements HasShrinkableNavBarHandlers {

    private int offset = 300;

    public MaterialNavBarShrink() {
        super();
        setInitialClasses(NavBarType.SHRINK.getCssName());
    }

    @Override
    protected void build() {
        super.build();
        $("header").css("position", "fixed");
        $("header").css("width", "100%");
        final boolean[] fired = {false};
        window().off().on("scroll", (e, param1) -> {
            int distanceY = window().scrollTop();

            if (distanceY > offset) {
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
            return true;
        });
    }

    @Override
    public HandlerRegistration addExpandHandler(NavBarExpandEvent.NavBarExpandHandler handler) {
        return addHandler(handler, NavBarExpandEvent.TYPE);
    }

    @Override
    public HandlerRegistration addShrinkHandler(NavBarShrinkEvent.NavBarShrinkHandler handler) {
        return addHandler(handler, NavBarShrinkEvent.TYPE);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
