package gwt.material.design.config;

public interface DialogConfig extends BaseWidgetConfig {

    @Key("GMD.Dialog.Overlay.Blur.Value")
    @DefaultIntValue(0)
    int OverlayBlur();

    @Key("GMD.Dialog.Overlay.Blur.Target")
    @DefaultStringArrayValue({""})
    String[] OverlayBlurTarget();

    @Key("GMD.Dialog.Overlay.Background.Color")
    @DefaultStringValue("black")
    String OverlayBackgroundColor();

    @Key("GMD.Dialog.Overlay.Opacity")
    @DefaultDoubleValue(0.4)
    double OverlayOpacity();
}
