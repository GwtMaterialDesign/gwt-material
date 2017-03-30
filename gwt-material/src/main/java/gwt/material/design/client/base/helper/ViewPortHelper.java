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

    public static boolean matchMedia(ViewPort viewPort) {
        return Window.matchMedia(viewPort.getMediaQuery());
    }
}
