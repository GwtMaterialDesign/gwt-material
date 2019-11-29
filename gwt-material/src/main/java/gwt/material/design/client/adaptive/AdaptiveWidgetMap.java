package gwt.material.design.client.adaptive;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.viewport.Resolution;
import gwt.material.design.client.base.viewport.WidthBoundary;

import java.util.HashMap;

public class AdaptiveWidgetMap<T extends Widget> extends HashMap<WidthBoundary, T> {

    public void put(Resolution resolution, T widget) {
        put(resolution.getBoundary(), widget);
    }
}
