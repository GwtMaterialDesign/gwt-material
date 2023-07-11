package gwt.material.design.client.constants;

import gwt.material.design.client.base.helper.EnumHelper;

public enum SymbolType implements CssType {

    OUTLINED("material-symbols-outlined"),
    ROUNDED("material-symbols-rounded"),
    SHARP("material-symbols-sharp");

    protected String name;

    SymbolType(String name) {
        this.name = name;
    }

    @Override
    public String getCssName() {
        return name;
    }

    public static SymbolType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, SymbolType.class, OUTLINED);
    }
}
