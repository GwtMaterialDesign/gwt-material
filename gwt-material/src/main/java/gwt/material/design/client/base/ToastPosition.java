package gwt.material.design.client.base;

public enum ToastPosition {
    DEFAULT(""),
    TOP_LEFT("top-left"),
    TOP_RIGHT("top-right"),
    BOTTOM_LEFT("bottom-left"),
    BOTTOM_RIGHT("bottom-right");

    private String name;

    ToastPosition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
