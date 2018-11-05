package gwt.material.design.client.async;

import com.google.gwt.user.client.ui.Widget;

public interface HasAsyncCallback<W extends Widget, V> {

    void setAsyncCallback(AsyncWidgetCallback<W, V> asyncCallback);

    AsyncWidgetCallback<W, V> getAsyncCallback();
}
