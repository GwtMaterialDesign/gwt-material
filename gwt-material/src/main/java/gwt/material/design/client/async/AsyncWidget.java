package gwt.material.design.client.async;

import gwt.material.design.client.async.events.HasAsyncHandlers;

public interface AsyncWidget extends Asynchronous, HasAsyncHandlers {

    void loading();

    void success();

    void failure();
}
