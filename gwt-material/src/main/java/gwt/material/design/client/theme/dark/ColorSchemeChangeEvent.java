package gwt.material.design.client.theme.dark;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public class ColorSchemeChangeEvent extends GwtEvent<ColorSchemeChangeEvent.ColorSchemeChangeHandler> {

    public interface ColorSchemeChangeHandler extends EventHandler {
        void onColorSchemeChange(ColorSchemeChangeEvent event);
    }

    private ColorScheme colorScheme;

    public ColorSchemeChangeEvent(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    public static final Type<ColorSchemeChangeEvent.ColorSchemeChangeHandler> TYPE = new Type<>();

    public static void fire(HasHandlers source, ColorScheme colorScheme) {
        source.fireEvent(new ColorSchemeChangeEvent(colorScheme));
    }

    @Override
    public Type<ColorSchemeChangeEvent.ColorSchemeChangeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ColorSchemeChangeEvent.ColorSchemeChangeHandler handler) {
        handler.onColorSchemeChange(this);
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }
}