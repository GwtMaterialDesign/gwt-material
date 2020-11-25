package gwt.material.design.client.base;

public enum CopyCommand {

    OFF("off"),
    ON_ALWAYS("on-always"),
    ON_READONLY("on-readonly"),
    ON_READONLY_HOVER("on-readonly-hover"),
    ON_ALWAYS_HOVER("on-always-hover");

    private String name;

    CopyCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
