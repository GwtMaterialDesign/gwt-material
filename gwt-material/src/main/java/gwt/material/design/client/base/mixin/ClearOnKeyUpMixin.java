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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.UIObject;
import gwt.material.design.client.base.AbstractValueWidget;
import gwt.material.design.client.base.HasClearOnKeyUp;
import gwt.material.design.client.constants.CssName;

/**
 * A mixin providing a keycode to be set (by default {@link KeyCodes#KEY_ESCAPE} to
 * reset the field values when {@link com.google.gwt.event.dom.client.KeyUpEvent} was fired.
 *
 * @author kevzlou7979
 */
public class ClearOnKeyUpMixin <T extends AbstractValueWidget & HasClearOnKeyUp, H extends UIObject & HasText>
        extends AbstractMixin<T> implements HasClearOnKeyUp {

    private H placeholder;
    private boolean clearOnKeyUp;
    private int keyCode;

    private ToggleStyleMixin<AbstractValueWidget> activeStyleMixin;

    public ClearOnKeyUpMixin(T uiObject, H placeholder) {
        this(uiObject, placeholder, KeyCodes.KEY_ESCAPE);
    }

    public ClearOnKeyUpMixin(T uiObject, H placeholder, int keyCode) {
        super(uiObject);

        this.placeholder = placeholder;
        setClearKeyCode(keyCode);
    }

    @Override
    public void setClearOnKeyUp(boolean clearOnKeyUp) {
        this.clearOnKeyUp = clearOnKeyUp;

        if (clearOnKeyUp) {
            if (!uiObject.isAttached()) {
                uiObject.addAttachHandler(event -> applyClearOnKeyUp());
            } else {
                applyClearOnKeyUp();
            }
        } else {
            resetClearOnKeyUp();
        }
    }

    protected void applyClearOnKeyUp() {
        if (uiObject != null){
            uiObject.addKeyUpHandler(event -> {
                if (event.getNativeKeyCode() == getClearKeyCode()) {
                    uiObject.reset();
                    setPlaceholderActive(true);
                }
            });
        } else {
            GWT.log("Target element to register the keyup event is null", new NullPointerException());
        }
    }

    protected void resetClearOnKeyUp() {
        setPlaceholderActive(false);;
    }

    public void setPlaceholderActive(boolean active) {
        if (placeholder != null) {
            getActiveStyleMixin().setOn(active);
        }
    }

    @Override
    public boolean isClearOnKeyUp() {
        return clearOnKeyUp;
    }

    @Override
    public void setClearKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public int getClearKeyCode() {
        return keyCode;
    }

    public ToggleStyleMixin<AbstractValueWidget> getActiveStyleMixin() {
        if (activeStyleMixin == null) {
            activeStyleMixin = new ToggleStyleMixin(placeholder, CssName.ACTIVE);
        }
        return activeStyleMixin;
    }
}
