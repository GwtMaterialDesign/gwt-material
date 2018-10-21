package gwt.material.design.client.ui.async;

import gwt.material.design.client.base.AbstractAsyncWidgetValueWidget;

public interface IsAsyncWidget<V> {

    void load();

    void setWidgetCallback(WidgetCallback<V, AbstractAsyncWidgetValueWidget> widgetCallback);
}
