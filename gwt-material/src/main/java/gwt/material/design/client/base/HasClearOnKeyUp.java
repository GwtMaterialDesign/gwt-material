package gwt.material.design.client.base;

/**
 * See {@link gwt.material.design.client.base.mixin.ClearOnKeyUpMixin}
 */
public interface HasClearOnKeyUp {

    /**
     * Will set if we enable clearing the field values after {@link com.google.gwt.event.dom.client.KeyUpEvent}
     * was fired. Enabled By default to all {@link AbstractValueWidget}
     */
    void setClearOnKeyUp(boolean value);

    /**
     * Check if clear on key up was enabled.
     */
    boolean isClearOnKeyUp();

    /**
     * Will set the keycode to be checked during the {@link com.google.gwt.event.dom.client.KeyUpEvent}
     * {@link com.google.gwt.event.dom.client.KeyCodes#KEY_ESCAPE} by Default.
     */
    void setClearKeyCode(int keyCode);

    /**
     * Will get the clear key code {@link com.google.gwt.event.dom.client.KeyCodes#KEY_ESCAPE} by default.
     */
    int getClearKeyCode();
}
