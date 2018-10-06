/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.base.mixin;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.*;
import gwt.material.design.client.base.HasStatusDisplayType;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.ui.MaterialIcon;

import static gwt.material.design.jquery.client.api.JQuery.$;

public class StatusDisplayMixin<T extends UIObject, H extends UIObject & HasText>
        extends AbstractMixin<T> implements HasStatusDisplayType {

    public enum StatusType {
        ERROR,
        SUCCESS
    }

    private H textObject;
    private StatusDisplayType displayType;
    private MaterialIcon statusIcon = new MaterialIcon();
    private Widget container;

    private CssNameMixin<T, StatusDisplayType> statusCssNameMixin;
    private CssNameMixin<H, Position> positionCssNameMixin;

    public StatusDisplayMixin(T uiObject, H textObject) {
        super(uiObject);

        this.textObject = textObject;
    }

    @Override
    public void setStatusDisplayType(StatusDisplayType displayType) {
        this.displayType = displayType;

        getStatusCssNameMixin().setCssName(displayType);
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
                ((MaterialWidget) uiObject).addKeyUpHandler(event -> {
                    if (event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE){
                        hideStatus();
                    }
                });
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

    public Position getPosition() {
        return getPositionCssNameMixin().getCssName();
    }

    public void setPosition(Position position) {
        getPositionCssNameMixin().setCssName(position);
    }

    protected void showStatus() {
        setPosition(Position.LEFT);

        if (getPosition() == Position.RIGHT) {
            textObject.getElement().getStyle().setProperty("left", "unset");
            textObject.getElement().getStyle().setProperty("right", "0px");
        }

        if (getPosition() == Position.LEFT) {
            textObject.getElement().getStyle().setProperty("right", "unset");
            textObject.getElement().getStyle().setProperty("left", ($(uiObject.getElement()).width() - 32) + "px");
        }

        if (getPosition() == Position.BOTTOM) {
            textObject.getElement().getStyle().setProperty("top", "-54px");
        }

        if (getPosition() == Position.TOP) {
            textObject.getElement().getStyle().setProperty("top", "54px");
        }

        textObject.getElement().getStyle().setVisibility(Style.Visibility.VISIBLE);
    }

    protected void hideStatus() {
        textObject.getElement().getStyle().setVisibility(Style.Visibility.HIDDEN);
    }

    public void setContainer(Widget container) {
        this.container = container;
    }

    protected CssNameMixin<T, StatusDisplayType> getStatusCssNameMixin() {
        if (statusCssNameMixin == null) {
            statusCssNameMixin = new CssNameMixin<>(uiObject);
        }
        return statusCssNameMixin;
    }

    protected CssNameMixin<H, Position> getPositionCssNameMixin() {
        if (positionCssNameMixin == null) {
            positionCssNameMixin = new CssNameMixin<>(textObject);
        }
        return positionCssNameMixin;
    }
}
