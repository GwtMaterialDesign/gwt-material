package gwt.material.design.client.custom.mixin;

import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.custom.HasColors;

/**
 * @author Ben Dol
 */
public class AlignMixin<T extends UIObject & HasColors> extends AbstractMixin implements HasColors {

    private String bgColor;
    private String textColor;

    public AlignMixin(final T widget) {
        super(widget);
    }

    @Override
    public void setBackgroundColor(String bgColor) {
        uiObject.removeStyleName(this.bgColor);
        this.bgColor = bgColor;

        if(bgColor != null && !bgColor.isEmpty()) {
            uiObject.addStyleName(bgColor);
        }
    }

    @Override
    public String getBackgroundColor() {
        return bgColor;
    }

    @Override
    public void setTextColor(String textColor) {
        uiObject.removeStyleName(this.textColor);
        this.textColor = textColor;

        if(textColor != null && !textColor.isEmpty()) {
            uiObject.addStyleName(textColor);
        }
    }

    @Override
    public String getTextColor() {
        return textColor;
    }
}
