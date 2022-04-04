package gwt.material.design.client.constants;

public enum IconDisplay {

    FILLED(""),
    OUTLINED("-outlined"),
    ROUNDED("-round"),
    SHARP("-sharp"),
    TWO_TONE("-two-tone");

    private String suffix;

    IconDisplay(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
