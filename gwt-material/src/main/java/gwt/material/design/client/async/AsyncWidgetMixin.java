package gwt.material.design.client.async;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.async.events.FailureEvent;
import gwt.material.design.client.async.events.HasAsyncHandlers;
import gwt.material.design.client.async.events.LoadingEvent;
import gwt.material.design.client.async.events.SuccessEvent;
import gwt.material.design.client.base.MaterialWidget;

public class AsyncWidgetMixin<T extends MaterialWidget> implements HasAsyncHandlers {

    private T widget;

    public AsyncWidgetMixin(T widget) {
        this.widget = widget;
    }

    @Override
    public HandlerRegistration addLoadingHandler(LoadingEvent.LoadingHandler handler) {
        return widget.addHandler(handler, LoadingEvent.getType());
    }

    @Override
    public HandlerRegistration addErrorHandler(FailureEvent.ErrorHandler handler) {
        return widget.addHandler(handler, FailureEvent.getType());
    }

    @Override
    public HandlerRegistration addSuccessHandler(SuccessEvent.SuccessHandler handler) {
        return widget.addHandler(handler, SuccessEvent.getType());
    }
}
