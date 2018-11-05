package gwt.material.design.client.async;

import gwt.material.design.client.async.renderer.AsyncRenderer;

public interface HasAsyncRenderer<X, T> {

    void setAsyncRender(AsyncRenderer<X, T> renderer);

    AsyncRenderer<X, T> getAsyncRenderer();
}
