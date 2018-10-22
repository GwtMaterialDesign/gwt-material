package gwt.material.design.client.async;

import gwt.material.design.client.async.events.HasAsyncHandlers;

public interface AsyncWidget extends Asynchronous, HasAsyncHandlers, AsyncLoader {

    void setAsyncLoader(AsyncLoader loader);

    AsyncLoader getAsyncLoader();
}
