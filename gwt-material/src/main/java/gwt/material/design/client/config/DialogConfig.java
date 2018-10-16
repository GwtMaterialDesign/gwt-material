package gwt.material.design.client.config;

public interface DialogConfig extends WidgetConfig {

    @Key("GMD.Dialog.Overlay.Blur.Value")
    @DefaultIntValue(0)
    int OverlayBlur();

    @Key("GMD.Dialog.Overlay.Blur.Target")
    String[] OverlayBlurTarget();

    @Key("GMD.Dialog.Overlay.Background.Color")
    @DefaultStringValue("black")
    String OverlayBackgroundColor();

    @Key("GMD.Dialog.Overlay.Opacity")
    @DefaultDoubleValue(0.4)
    double OverlayOpacity();
}
