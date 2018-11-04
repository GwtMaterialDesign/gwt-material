package gwt.material.design.client.async;

import gwt.material.design.client.async.loader.AsyncDisplayLoader;

public interface HasAsyncDisplayLoader {

    void setAsyncDisplayLoader(AsyncDisplayLoader displayLoader);

    AsyncDisplayLoader getAsyncDisplayLoader();
}
