package gwt.material.design.client.ui.animate;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
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

import gwt.material.design.client.base.helper.EnumHelper;
import gwt.material.design.client.constants.CssType;

public enum Transition implements CssType {
    // Material Animation Components
    SHOW_STAGGERED_LIST("staggered_list"),
    FADE_IN_IMAGE("fade_in_image"),
    SHOW_GRID("show_grid"),
    CLOSE_GRID("close_grid"),

    // Core Animation Components
    BOUNCE("bounce"),
    FLASH("flash"),
    PULSE("pulse"),
    RUBBERBAND("rubberBand"),
    SHAKE("shake"),
    HEADSHAKE("headShake"),
    SWING("swing"),
    TADA("tada"),
    WOBBLE("wobble"),
    JELLO("jello"),
    BOUNCEIN("bounceIn"),
    BOUNCEINDOWN("bounceInDown"),
    BOUNCEINLEFT("bounceInLeft"),
    BOUNCEINRIGHT("bounceInRight"),
    BOUNCEINUP("bounceInUp"),
    BOUNCEOUT("bounceOut"),
    BOUNCEOUTDOWN("bounceOutDown"),
    BOUNCEOUTLEFT("bounceOutLeft"),
    BOUNCEOUTRIGHT("bounceOutRight"),
    BOUNCEOUTUP("bounceOutUp"),
    FADEIN("fadeIn"),
    FADEINDOWN("fadeInDown"),
    FADEINDOWNBIG("fadeInDownBig"),
    FADEINLEFT("fadeInLeft"),
    FADEINLEFTBIG("fadeInLeftBig"),
    FADEINRIGHT("fadeInRight"),
    FADEINRIGHTBIG("fadeInRightBig"),
    FADEINUP("fadeInUp"),
    FADEINUPBIG("fadeInUpBig"),
    FADEOUT("fadeOut"),
    FADEOUTDOWN("fadeOutDown"),
    FADEOUTDOWNBIG("fadeOutDownBig"),
    FADEOUTLEFT("fadeOutLeft"),
    FADEOUTLEFTBIG("fadeOutLeftBig"),
    FADEOUTRIGHT("fadeOutRight"),
    FADEOUTRIGHTBIG("fadeOutRightBig"),
    FADEOUTUP("fadeOutUp"),
    FADEOUTUPBIG("fadeOutUpBig"),
    FLIPINX("flipInX"),
    FLIPINY("flipInY"),
    FLIPOUTX("flipOutX"),
    FLIPOUTY("flipOutY"),
    LIGHTSPEEDIN("lightSpeedIn"),
    LIGHTSPEEDOUT("lightSpeedOut"),
    ROTATEIN("rotateIn"),
    ROTATEINDOWNLEFT("rotateInDownLeft"),
    ROTATEINDOWNRIGHT("rotateInDownRight"),
    ROTATEINUPLEFT("rotateInUpLeft"),
    ROTATEINUPRIGHT("rotateInUpRight"),
    ROTATEOUT("rotateOut"),
    ROTATEOUTDOWNLEFT("rotateOutDownLeft"),
    ROTATEOUTDOWNRIGHT("rotateOutDownRight"),
    ROTATEOUTUPLEFT("rotateOutUpLeft"),
    ROTATEOUTUPRIGHT("rotateOutUpRight"),
    HINGE("hinge"),
    ROLLIN("rollIn"),
    ROLLOUT("rollOut"),
    ZOOMIN("zoomIn"),
    ZOOMINDOWN("zoomInDown"),
    ZOOMINLEFT("zoomInLeft"),
    ZOOMINRIGHT("zoomInRight"),
    ZOOMINUP("zoomInUp"),
    ZOOMOUT("zoomOut"),
    ZOOMOUTDOWN("zoomOutDown"),
    ZOOMOUTLEFT("zoomOutLeft"),
    ZOOMOUTRIGHT("zoomOutRight"),
    ZOOMOUTUP("zoomOutUp"),
    SLIDEINDOWN("slideInDown"),
    SLIDEINLEFT("slideInLeft"),
    SLIDEINRIGHT("slideInRight"),
    SLIDEINUP("slideInUp"),
    SLIDEOUTDOWN("slideOutDown"),
    SLIDEOUTLEFT("slideOutLeft"),
    SLIDEOUTRIGHT("slideOutRight"),
    SLIDEOUTUP("slideOutUp");

    private final String cssClass;

    Transition(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static Transition fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, Transition.class, SHOW_STAGGERED_LIST);
    }
}
