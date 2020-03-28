package gwt.material.design.client.ui.animate.debugger;

public enum AnimationSpeed {

    SLOWEST(4),
    SLOWER(3),
    SLOW(2),
    NORMAL(1),
    FAST(0.75),
    FASTER(0.5),
    FASTEST(0.125);

    protected double value;

    AnimationSpeed(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
