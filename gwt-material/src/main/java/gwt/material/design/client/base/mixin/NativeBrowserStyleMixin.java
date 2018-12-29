package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasNativeBrowserStyle;

public class NativeBrowserStyleMixin<T extends UIObject & HasNativeBrowserStyle> extends AbstractMixin<T> implements HasNativeBrowserStyle {

    private ToggleStyleMixin<T> toggleNativeBrowserStyleMixin;

    public NativeBrowserStyleMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setNativeBrowserStyle(boolean value) {
        getToggleNativeBrowserStyleMixin().setOn(value);
    }

    @Override
    public boolean isNativeBrowserStyle() {
        return getToggleNativeBrowserStyleMixin().isOn();
    }

    public ToggleStyleMixin<T> getToggleNativeBrowserStyleMixin() {
        if (toggleNativeBrowserStyleMixin == null) {
            toggleNativeBrowserStyleMixin = new ToggleStyleMixin<>(uiObject, "browser-default");
        }
        return toggleNativeBrowserStyleMixin;
    }
}