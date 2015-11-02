package gwt.material.design.client.constants;

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.custom.helper.EnumHelper;

public enum Alignment implements Style.HasCssName {
    DEFAULT(""),
    LEFT("left"),
    RIGHT("right"),
    CENTER("center");

    private final String cssClass;

    private Alignment(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static Alignment fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, Alignment.class, DEFAULT);
    }
}
