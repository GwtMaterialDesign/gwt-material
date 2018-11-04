package gwt.material.design.client.async.loader;

import gwt.material.design.client.ui.MaterialCheckBox;

public class DefaultCheckBoxDisplayLoader implements AsyncDisplayLoader<Boolean> {

    private MaterialCheckBox checkBox;

    public DefaultCheckBoxDisplayLoader(MaterialCheckBox checkBox) {
        this.checkBox = checkBox;
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
