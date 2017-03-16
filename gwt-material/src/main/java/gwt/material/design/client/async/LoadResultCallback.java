package gwt.material.design.client.async;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class LoadResultCallback<T, H extends HasAsyncWidget> implements AsyncCallback<T> {

    private H asyncWidget;

    public LoadResultCallback(H asyncWidget) {
        this.asyncWidget = asyncWidget;
    }

    public void load() {
        asyncWidget.showLoading();
    }

    public void onSuccess(T object) {
        asyncWidget.hideLoading();
    }

    public void onFailure(java.lang.Throwable throwable) {
        asyncWidget.hideLoading();
    }
}
