package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasContainer;
import gwt.material.design.client.constants.CssName;

public class ContainerMixin<T extends UIObject & HasContainer> extends AbstractMixin<T>
    implements HasContainer {

    private ToggleStyleMixin<T> containerEnabledMixin;
    private ToggleStyleMixin<T> valignWrapperMixin;

    public ContainerMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setContainerEnabled(boolean value) {
        getContainerEnabledMixin().setOn(value);
    }

    @Override
    public boolean isContainerEnabed() {
        return getContainerEnabledMixin().isOn();
    }

    @Override
    public void setValignWrapper(boolean value) {
        getValignWrapperMixin().setOn(value);
    }

    @Override
    public boolean isValignWrapper() {
        return getValignWrapperMixin().isOn();
    }

    protected ToggleStyleMixin<T> getContainerEnabledMixin() {
        if (containerEnabledMixin == null) {
            containerEnabledMixin = new ToggleStyleMixin(uiObject, CssName.CONTAINER);
        }
        return containerEnabledMixin;
    }

    protected ToggleStyleMixin<T> getValignWrapperMixin() {
        if (valignWrapperMixin == null) {
            valignWrapperMixin = new ToggleStyleMixin<>(uiObject, CssName.VALIGN_WRAPPER);
        }
        return valignWrapperMixin;
    }
}
