package gwt.material.design.client.base.mixin;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.base.HasDensity;
import gwt.material.design.client.base.density.DisplayDensity;
import gwt.material.design.client.base.helper.EventHelper;

public class DensityMixin<T extends Widget> extends AbstractMixin<T> implements HasDensity {

    private CssNameMixin<Widget, DisplayDensity> styleMixin;
    private DisplayDensity density = DisplayDensity.DEFAULT;

    public DensityMixin(T uiObject) {
        super(uiObject);
    }

    @Override
    public void setDensity(DisplayDensity density) {
        this.density = density;

        if (uiObject.isAttached()) {
            getStyleMixin().setCssName(density);
        } else {
            EventHelper.onAttachOnce(uiObject, attachEvent -> getStyleMixin().setCssName(density));
        }
    }

    @Override
    public DisplayDensity getDensity() {
        return DisplayDensity.fromStyleName(getStyleMixin().getStyle());
    }

    public CssNameMixin<Widget, DisplayDensity> getStyleMixin() {
        if (styleMixin == null) {
            styleMixin = new CssNameMixin<>(uiObject);
        }
        return styleMixin;
    }
}
