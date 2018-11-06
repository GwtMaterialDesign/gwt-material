package gwt.material.design.client.async.loader;

import com.google.gwt.core.client.Scheduler;
import gwt.material.design.client.async.renderer.AsyncRenderer;
import gwt.material.design.client.constants.LoaderType;
import gwt.material.design.client.ui.MaterialListValueBox;
import gwt.material.design.client.ui.MaterialLoader;

import java.util.List;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class DefaultListValueBoxLoader<T> implements AsyncDisplayLoader<List<T>> {

    private MaterialLoader loader;
    private MaterialListValueBox<T> listValueBox;

    protected DefaultListValueBoxLoader() {}

    public DefaultListValueBoxLoader(MaterialListValueBox<T> listValueBox) {
        this.listValueBox = listValueBox;

        setupLoader();
    }

    protected void setupLoader() {
        loader = new MaterialLoader();
        loader.setContainer(listValueBox);
        loader.setType(LoaderType.PROGRESS);
    }

    @Override
    public void loading() {
        loader.show();
    }

    @Override
    public void success(List<T> result) {
        result.forEach(object -> listValueBox.addItem(object, listValueBox.getAsyncRenderer() != null ? listValueBox.getAsyncRenderer().render(object) : object.toString(), false));
        listValueBox.reload();
        $(listValueBox.getElement()).find("input").focus();
        listValueBox.setLoaded(true);
    }

    @Override
    public void failure(String error) {

    }

    @Override
    public void finalize() {
        loader.hide();
    }
}
