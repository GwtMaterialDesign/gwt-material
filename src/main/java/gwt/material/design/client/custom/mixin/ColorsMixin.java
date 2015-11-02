package gwt.material.design.client.custom.mixin;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.custom.HasWaves;
import gwt.material.design.client.custom.Waves;

/**
 * @author Ben Dol
 */
public class ColorsMixin<T extends Widget & HasWaves> extends AbstractMixin implements HasWaves {

    private T widget;
    private WavesType waves;

    public ColorsMixin(final T widget) {
        super(widget);

        this.widget = widget;
    }

    @Override
    public void setWaves(WavesType waves) {
        uiObject.removeStyleName("waves-effect waves-" + this.waves);
        this.waves = waves;

        if(waves != null) {
            uiObject.addStyleName("waves-effect waves-" + waves);
            Waves.detectAndApply(widget);
        }
    }

    @Override
    public WavesType getWaves() {
        return waves;
    }
}
