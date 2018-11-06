package gwt.material.design.client.async.loader;

import gwt.material.design.client.constants.LoaderType;
import gwt.material.design.client.ui.MaterialCollapsibleBody;
import gwt.material.design.client.ui.MaterialCollapsibleItem;
import gwt.material.design.client.ui.MaterialLoader;

public class DefaultCollapsibleItemLoader<T> implements AsyncDisplayLoader<T> {

    private MaterialCollapsibleItem item;
    private MaterialLoader loader;

    public DefaultCollapsibleItemLoader(MaterialCollapsibleItem item) {
        this.item = item;
        setupLoader();
    }

    protected void setupLoader() {
        loader = new MaterialLoader();
        loader.setContainer(item);
        loader.setType(LoaderType.PROGRESS);
    }

    @Override
    public void loading() {
        loader.show();
    }

    @Override
    public void success(T result) {
        MaterialCollapsibleBody body = (MaterialCollapsibleBody) item.getAsyncRenderer().render(result);
        item.add(body);
        item.expand();
        item.setLoaded(true);
    }

    @Override
    public void failure(String error) {

    }

    @Override
    public void finalize() {
        loader.hide();
    }
}
