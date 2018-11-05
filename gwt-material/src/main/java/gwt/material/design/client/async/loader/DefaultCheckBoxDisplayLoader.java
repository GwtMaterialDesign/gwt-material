package gwt.material.design.client.async.loader;

import gwt.material.design.client.ui.MaterialCheckBox;
import gwt.material.design.client.ui.MaterialLoader;

public class DefaultCheckBoxDisplayLoader implements AsyncDisplayLoader<Boolean> {

    private MaterialCheckBox checkBox;
    private MaterialLoader loader;

    protected DefaultCheckBoxDisplayLoader() {}

    public DefaultCheckBoxDisplayLoader(MaterialCheckBox checkBox) {
        this.checkBox = checkBox;

        setupLoader();
    }

    protected void setupLoader() {
        loader = new MaterialLoader();
    }

    @Override
    public void loading() {
        checkBox.setEnabled(false);
    }

    @Override
    public void success(Boolean result) {
        checkBox.setValue(result, true);
    }

    @Override
    public void failure(String error) {

    }

    @Override
    public void finalize() {
        checkBox.setEnabled(true);
    }
}
