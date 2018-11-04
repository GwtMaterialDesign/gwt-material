package gwt.material.design.client.async;

public interface HasAsyncCallback {

    void setAsyncCallback(AsyncWidgetCallback asyncCallback);

    AsyncWidgetCallback getAsyncCallback();
}
