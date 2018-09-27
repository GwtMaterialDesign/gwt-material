package gwt.material.design.client.base.mixin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.HasStatusDisplayType;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.StatusDisplayType;
import gwt.material.design.client.ui.MaterialIcon;

public class StatusDisplayMixin<T extends UIObject , H extends UIObject & HasText>
        extends AbstractMixin<T> implements HasStatusDisplayType {

    public enum StatusType {
        ERROR,
        SUCCESS
    }

    private H textObject;
    private StatusDisplayType displayType;
    private MaterialIcon statusIcon = new MaterialIcon();
    private CssNameMixin<T, StatusDisplayType> cssNameMixin;
    private Panel statusIconContainer;

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

    public void setStatusDisplay(StatusType statusType) {
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

            if (statusIconContainer != null && statusIconContainer.isAttached()) {
                statusIconContainer.add(statusIcon);
            } else {
                ((HasWidgets) uiObject).add(statusIcon);
            }

            if (uiObject instanceof HasWidgets && uiObject instanceof MaterialWidget) {
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

    public Panel getStatusIconContainer() {
        return statusIconContainer;
    }

    public void setStatusIconContainer(Panel statusIconContainer) {
        this.statusIconContainer = statusIconContainer;
    }

    public CssNameMixin<T, StatusDisplayType> getCssNameMixin() {
        if (cssNameMixin == null) {
            cssNameMixin = new CssNameMixin<>(uiObject);
        }
        return cssNameMixin;
    }
}
