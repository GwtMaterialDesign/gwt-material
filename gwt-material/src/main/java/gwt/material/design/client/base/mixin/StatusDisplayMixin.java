package gwt.material.design.client.base.mixin;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;
import gwt.material.design.client.base.HasStatusDisplayType;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.StatusDisplayType;
import gwt.material.design.client.ui.MaterialIcon;
import gwt.material.design.client.ui.MaterialToast;

public class StatusDisplayMixin<T extends UIObject, H extends UIObject & HasText>
        extends AbstractMixin<T> implements HasStatusDisplayType {

    public enum StatusType {
        ERROR,
        SUCCESS
    }

    private H textObject;
    private StatusDisplayType displayType;
    private MaterialIcon statusIcon = new MaterialIcon();
    private CssNameMixin<T, StatusDisplayType> cssNameMixin;
    private Widget container;

    public StatusDisplayMixin(T uiObject, H textObject) {
        super(uiObject);

        this.textObject = textObject;
    }

    @Override
    public void setStatusDisplayType(StatusDisplayType displayType) {
        this.displayType = displayType;

        getCssNameMixin().setCssName(displayType);
    }

    @Override
    public StatusDisplayType getStatusDisplayType() {
        return displayType;
    }

    @Override
    public void updateStatusDisplay(StatusType statusType) {
        if (displayType == StatusDisplayType.HOVERABLE) {

            if (!statusIcon.getElement().hasClassName(CssName.STATUS_ICON)) {
                statusIcon.addStyleName(CssName.STATUS_ICON);
            }

            if (statusType == StatusType.ERROR) {
                statusIcon.setIconType(IconType.ERROR);
                statusIcon.setIconColor(Color.RED);
            } else {
                statusIcon.setIconType(IconType.CHECK_CIRCLE);
                statusIcon.setIconColor(Color.GREEN);
            }

            if (uiObject instanceof HasWidgets && uiObject instanceof MaterialWidget) {
                if (container != null && container instanceof MaterialWidget) {
                    ((MaterialWidget) container).insert(statusIcon, 0);
                } else {
                    ((HasWidgets) uiObject).add(statusIcon);
                }

                statusIcon.addMouseOverHandler(event -> showStatus());
                statusIcon.addMouseOutHandler(event -> hideStatus());
                ((MaterialWidget) uiObject).addFocusHandler(event -> showStatus());
                ((MaterialWidget) uiObject).addBlurHandler(event -> hideStatus());
            }
        } else {
            resetStatusDisplay();
        }
    }

    public void resetStatusDisplay() {
        if (statusIcon != null && statusIcon.isAttached()) {
            statusIcon.removeFromParent();
        }

        if (textObject != null) {
            textObject.getElement().getStyle().clearVisibility();
        }
    }

    protected void showStatus() {
        textObject.getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);
    }

    protected void hideStatus() {
        textObject.getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);
    }

    public void setContainer(Widget container) {
        this.container = container;
    }

    public CssNameMixin<T, StatusDisplayType> getCssNameMixin() {
        if (cssNameMixin == null) {
            cssNameMixin = new CssNameMixin<>(uiObject);
        }
        return cssNameMixin;
    }
}
