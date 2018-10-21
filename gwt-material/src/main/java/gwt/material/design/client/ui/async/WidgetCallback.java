package gwt.material.design.client.ui.async;

import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.base.AbstractAsyncWidgetValueWidget;

public interface WidgetCallback<T, W extends AbstractAsyncWidgetValueWidget> {

    void load(AsyncCallback<T> callback, W widget);
}
