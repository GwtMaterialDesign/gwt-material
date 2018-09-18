package gwt.material.design.client.constants;

import gwt.material.design.client.base.helper.EnumHelper;

public enum StatusDisplayType implements CssType {

    DEFAULT(""),
    HOVERABLE("hoverable-status");

    private final String cssClass;

    StatusDisplayType(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static StatusDisplayType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, StatusDisplayType.class, DEFAULT);
    }
}