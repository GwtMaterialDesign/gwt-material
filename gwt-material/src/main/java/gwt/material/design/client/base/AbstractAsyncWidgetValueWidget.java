package gwt.material.design.client.base;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import gwt.material.design.client.ui.async.IsAsyncWidget;
import gwt.material.design.client.ui.async.WidgetCallback;

public abstract class AbstractAsyncWidgetValueWidget<V extends Object> extends AbstractValueWidget<V> implements IsAsyncWidget<V> {

    protected WidgetCallback<V, AbstractAsyncWidgetValueWidget> widgetCallback;

    public AbstractAsyncWidgetValueWidget(Element element) {
        super(element);
    }

    public AbstractAsyncWidgetValueWidget(Element element, String... initialClass) {
        super(element, initialClass);
    }

    @Override
    public void load() {
        onLoading();
        widgetCallback.load(new AsyncCallback<V>() {
            @Override
            public void onFailure(Throwable caught) {
                AbstractAsyncWidgetValueWidget.this.onFailure();
                AbstractAsyncWidgetValueWidget.this.onFinalize();
            }

            @Override
            public void onSuccess(V result) {
                AbstractAsyncWidgetValueWidget.this.onSuccess();
                AbstractAsyncWidgetValueWidget.this.onFinalize();
            }
        }, this);
    }

    protected void onLoading() {}

    protected void onSuccess() {}

    protected void onFailure() {}

    protected void onFinalize() {}

    @Override
    public void setWidgetCallback(WidgetCallback widgetCallback) {
        this.widgetCallback = widgetCallback;
    }
}
