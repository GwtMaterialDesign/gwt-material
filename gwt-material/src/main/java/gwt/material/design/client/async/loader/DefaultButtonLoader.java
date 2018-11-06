package gwt.material.design.client.async.loader;

import gwt.material.design.client.base.AbstractIconButton;
import gwt.material.design.client.base.mixin.ToggleStyleMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.IconType;

public class DefaultButtonLoader implements AsyncIconDisplayLoader<String> {

    protected String initialText;
    protected IconType initialIcon;
    protected AbstractIconButton button;
    protected ToggleStyleMixin<AbstractIconButton> loadingStyleMixin;

    protected DefaultButtonLoader() {}

    public DefaultButtonLoader(AbstractIconButton button) {
        this.button = button;
        if (!button.isAttached()) {
            button.addAttachHandler(event -> {
                this.initialText = button.getText();
                this.initialIcon = button.getIcon().getIconType();
            });
        }
    }

    @Override
    public void loading() {
        button.setEnabled(false);
        button.setText("Loading");
        button.setIconType(getLoadingIcon());
        getLoadingStyleMixin().setOn(true);
    }

    @Override
    public void success(String result) {
        button.setIconType(getSuccessIcon());
    }

    @Override
    public void failure(String error) {
        button.setIconType(getErrorIcon());
    }

    @Override
    public void finalize() {
        button.setEnabled(true);
        getLoadingStyleMixin().setOn(false);
        if (initialText != null) {
            button.setText(initialText);
        }

        if (initialIcon != null) {
            button.setIconType(initialIcon);
        }
    }


    @Override
    public IconType getLoadingIcon() {
        return IconType.AUTORENEW;
    }

    @Override
    public IconType getSuccessIcon() {
        return IconType.CHECK_CIRCLE;
    }

    @Override
    public IconType getErrorIcon() {
        return IconType.WARNING;
    }

    protected ToggleStyleMixin<AbstractIconButton> getLoadingStyleMixin() {
        if (loadingStyleMixin == null) {
            loadingStyleMixin = new ToggleStyleMixin<>(button, CssName.LOADING);
        }
        return loadingStyleMixin;
    }
}
