package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasFullscreen;
import gwt.material.design.client.constants.CssName;

public class FullscreenMixin<T extends UIObject & HasFullscreen> extends AbstractMixin<T> implements HasFullscreen {


    private ToggleStyleMixin toggleStyleMixin = new ToggleStyleMixin<>(uiObject, CssName.FULLSCREEN);

    public FullscreenMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setFullscreen(boolean value) {
        toggleStyleMixin.setOn(value);
    }

    @Override
    public boolean isFullscreen() {
        return toggleStyleMixin.isOn();
    }
}
