package gwt.material.design.client.constants;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.custom.helper.EnumHelper;

public enum Position implements Style.HasCssName {
    BOTTOM("bottom"),
    TOP("top"),
    LEFT("left"),
    RIGHT("right");

    private final String cssClass;

    Position(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static Position fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, Position.class, BOTTOM);
    }
}
