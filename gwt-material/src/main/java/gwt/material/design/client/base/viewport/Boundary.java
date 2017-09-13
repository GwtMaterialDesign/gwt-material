package gwt.material.design.client.base.viewport;

import java.io.Serializable;

public interface Boundary extends Serializable {
    int getMin();
    int getMax();
    String asMediaQuery();
}
