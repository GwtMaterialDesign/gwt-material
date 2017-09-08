package gwt.material.design.client.constants;

public enum Shade implements CssType {
    LIGHTEN("lighten"),
    DARKEN("darken"),
    ACCENT("accent");

    private final String cssClass;

    Shade(final String cssClass) {
        this.cssClass = cssClass;
    }

    @Override
    public String getCssName() {
        return cssClass;
    }

    public static boolean isShade(String styleName) {
        for (final Shade shade : Shade.class.getEnumConstants()) {
            if(styleName.contains(shade.getCssName() + "-")) {
                return true;
            }
        }
        return false;
    }
}
