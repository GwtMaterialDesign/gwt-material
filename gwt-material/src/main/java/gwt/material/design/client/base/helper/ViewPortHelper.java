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
package gwt.material.design.client.base.helper;

import gwt.material.design.client.js.Window;

public class ViewPortHelper {

    public enum ViewPort {
        MOBILE_SMALL("(max-width: 320px)"),
        MOBILE_MEDIUM("(min-width: 321px) and (max-width: 375px)"),
        MOBILE_LARGE("(min-width: 376px) and (max-width: 425px)"),
        TABLET("(min-width: 426px) and (max-width: 768px)"),
        LAPTOP("(min-width: 769px) and (max-width: 1024px)"),
        LAPTOP_LARGE("(min-width: 1025px) and (max-width: 1440px)"),
        LAPTOP_4K("(min-width: 1441px) and (max-width: 2560px)");

        private String mediaQuery;

        ViewPort(String mediaQuery) {
            this.mediaQuery = mediaQuery;
        }

        public String getMediaQuery() {
            return mediaQuery;
        }

        public void setMediaQuery(String mediaQuery) {
            this.mediaQuery = mediaQuery;
        }
    }

    public static boolean isMobile() {
        return matchMedia(ViewPort.MOBILE_SMALL) || matchMedia(ViewPort.MOBILE_MEDIUM ) || matchMedia(ViewPort.MOBILE_LARGE);
    }

    public static boolean isTablet() {
        return matchMedia(ViewPort.TABLET);
    }

    public static boolean isLaptop() {
        return matchMedia(ViewPort.LAPTOP) || matchMedia(ViewPort.LAPTOP_LARGE) || matchMedia(ViewPort.LAPTOP_4K);
    }

    public static native boolean isTouchScreenDevice() /*-{
        return 'ontouchstart' in document.documentElement;
    }-*/;

    public static boolean matchMedia(ViewPort viewPort) {
        return Window.matchMedia(viewPort.getMediaQuery());
    }
}
