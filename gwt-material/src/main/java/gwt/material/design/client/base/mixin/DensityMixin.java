package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasDensity;
import gwt.material.design.client.base.density.DisplayDensity;
import gwt.material.design.client.base.helper.EventHelper;

public class DensityMixin<T extends Widget> extends AbstractMixin<T> implements HasDensity {

    private StyleMixin<Widget> styleMixin;

    public DensityMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setDensity(DisplayDensity displayDensity) {
        if (uiObject != null) {
            if (uiObject.isAttached()) {
                getStyleMixin().setStyle(displayDensity.getCssName());
            } else {
                EventHelper.onAttachOnce(uiObject, event -> getStyleMixin().setStyle(displayDensity.getCssName()));
            }
        }
    }

    @Override
    public DisplayDensity getDensity() {
        return DisplayDensity.fromStyleName(getStyleMixin().getStyle());
    }

    public StyleMixin<Widget> getStyleMixin() {
        if (styleMixin == null) {
            styleMixin = new StyleMixin<>(uiObject);
        }
        return styleMixin;
    }
}
