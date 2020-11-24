package gwt.material.design.client.base;

public enum CopyCommand {

    OFF("off"),
    ON_ALWAYS("on-always"),
    ON_READ_ONLY("on-read-only"),
    ON_ALWAYS_HOVER("on-always-hover");

    private String name;

    CopyCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
