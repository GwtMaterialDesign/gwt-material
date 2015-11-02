package gwt.material.design.client.custom.mixin;

/**
 * @author Ben Dol
 */
public class WavesMixin<T extends UIObject & HasActive> extends AbstractMixin implements HasActive {

    public WavesMixin(final T uiObject) {
        super(uiObject);
    }

    @Override
    public void setActive(final boolean active) {
        if (active) {
            uiObject.addStyleName(Styles.ACTIVE);
        } else {
            uiObject.removeStyleName(Styles.ACTIVE);
        }
    }

    @Override
    public boolean isActive() {
        return StyleHelper.containsStyle(uiObject.getStyleName(), Styles.ACTIVE);
    }
}
