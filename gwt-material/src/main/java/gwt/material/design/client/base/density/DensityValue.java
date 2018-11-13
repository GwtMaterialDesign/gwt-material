package gwt.material.design.client.base.density;

public class DensityValue implements Density {

    private int value;

    protected DensityValue() {}

    public DensityValue(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
