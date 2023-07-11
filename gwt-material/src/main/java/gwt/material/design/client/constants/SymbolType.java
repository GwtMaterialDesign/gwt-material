package gwt.material.design.client.constants;

import gwt.material.design.client.base.helper.EnumHelper;

public enum SymbolType implements CssType {

    OUTLINED("material-symbols-outlined", "https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"),
    ROUNDED("material-symbols-rounded", "https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"),
    SHARP("material-symbols-sharp", "https://fonts.googleapis.com/css2?family=Material+Symbols+Sharp:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200");

    protected String name;
    protected String cssLink;

    SymbolType(String name, String cssLink) {
        this.name = name;
        this.cssLink = cssLink;
    }

    @Override
    public String getCssName() {
        return name;
    }

    public String getCssLink() {
        return cssLink;
    }

    public static SymbolType fromStyleName(final String styleName) {
        return EnumHelper.fromStyleName(styleName, SymbolType.class, OUTLINED);
    }
}
