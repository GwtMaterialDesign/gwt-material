package gwt.material.design.client.base.viewport;

public class WidthBoundary implements Boundary {

    private int minWidth, maxWidth;

    protected WidthBoundary() {
    }

    public WidthBoundary(int minWidth, int maxWidth) {
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
    }

    @Override
    public int getMin() {
        return minWidth;
    }

    @Override
    public int getMax() {
        return maxWidth;
    }

    @Override
    public String asMediaQuery() {
        return "(min-width: " + getMin() + "px) and (max-width: " + getMax() + "px)";
    }
}
