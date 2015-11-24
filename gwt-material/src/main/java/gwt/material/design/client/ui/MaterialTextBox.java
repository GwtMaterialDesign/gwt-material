package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.AutoDirectionHandler;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.TextBox;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.CounterMixin;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.html.Label;

import java.util.List;

//@formatter:off

/**
* Material Text Box is an input field that accepts any text based string from user.
* <h3>UiBinder Usage:</h3>
* <pre>
*{@code <m:MaterialTextBox placeholder="First Name" />
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material TextBox</a>
* @author kevzlou7979
* @author Ben Dol
*/
//@formatter:on
public class MaterialTextBox extends ComplexWidget implements HasChangeHandlers, HasName, HasDirectionEstimator,
        HasValue<String>, HasText, AutoDirectionHandler.Target, IsEditor<ValueBoxEditor<String>>, HasKeyUpHandlers,
        HasClickHandlers, HasDoubleClickHandlers, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasIcon,
        HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasError, HasInputType,
        HasPlaceholder, HasCounter, HasEditorErrors<String> {


    private String placeholder;
    private InputType type = InputType.TEXT;
    private boolean isValid = true;

    private MaterialLabel lblError = new MaterialLabel();

    private Label label = new Label();
    private MaterialLabel lblName = new MaterialLabel();
    private TextBox txtBox = new TextBox();
    private MaterialIcon icon = new MaterialIcon();
    private CounterMixin<MaterialTextBox> counterMixin = new CounterMixin<>(this);

    private final ErrorMixin<MaterialTextBox, MaterialLabel> errorMixin = new ErrorMixin<>(this, lblError, txtBox);

    public MaterialTextBox() {
        super(Document.get().createDivElement());
        setStyleName("input-field");
        add(icon);
        add(txtBox);
        txtBox.setStyleName("validate");
        add(label);
        label.add(lblName);
        lblError.setVisible(false);
        add(lblError);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        String id = DOM.createUniqueId();
        txtBox.getElement().setId(id);
        label.getElement().setAttribute("for", id);
    }

    /**
     * Resets the textbox by removing its content and resetting visual state.
     */
    public void clear() {
        txtBox.setText("");
        clearErrorOrSuccess();
        label.removeStyleName("active");
    }

    public void removeErrorModifiers() {
        txtBox.getElement().removeClassName("valid");
        txtBox.getElement().removeClassName("invalid");
        lblName.removeStyleName("green-text");
        lblName.removeStyleName("red-text");
    }

    @Override
    public String getText() {
        return txtBox.getText();
    }

    @Override
    public void setText(String text) {
        txtBox.setText(text);

        if (text != null && !text.isEmpty()) {
            label.addStyleName("active");
        }
    }

    @Override
    public String getPlaceholder() {
        return placeholder;
    }

    @Override
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        lblName.setText(placeholder);
    }

    @Override
    public InputType getType() {
        return type;
    }

    @Override
    public void setType(InputType type) {
        this.type = type;
        txtBox.getElement().setAttribute("type", type.getType());

        if(type.equals(InputType.NUMBER)) {
            txtBox.addKeyPressHandler(new KeyPressHandler() {
                @Override
                public void onKeyPress(KeyPressEvent event) {
                     if (!Character.isDigit(event.getCharCode())
                            && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_TAB
                            && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE
                            && event.getNativeEvent().getKeyCode() != 190) {
                        ((TextBox) event.getSource()).cancelKey();
                     }
                }
            });
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
        return txtBox.addValueChangeHandler(handler);
    }

    @Override
    public String getValue() {
        return txtBox.getValue();
    }

    @Override
    public void setValue(String value) {
        setValue(value, false);
    }

    @Override
    public void setValue(String value, boolean fireEvents) {
        txtBox.setValue(value, fireEvents);

        if (!value.isEmpty()) {
            label.addStyleName("active");
        }
    }

    @Override
    public void setDirection(Direction direction) {
        txtBox.setDirection(direction);
    }

    @Override
    public Direction getDirection() {
        return txtBox.getDirection();
    }

    @Override
    public ValueBoxEditor<String> asEditor() {
        return txtBox.asEditor();
    }

    @Override
    public DirectionEstimator getDirectionEstimator() {
        return txtBox.getDirectionEstimator();
    }

    @Override
    public void setDirectionEstimator(boolean enabled) {
        txtBox.setDirectionEstimator(enabled);
    }

    @Override
    public void setDirectionEstimator(DirectionEstimator directionEstimator) {
        txtBox.setDirectionEstimator(directionEstimator);
    }

    @Override
    public void setName(String name) {
        txtBox.setName(name);
    }

    @Override
    public String getName() {
        return txtBox.getName();
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return addDomHandler(handler, KeyUpEvent.getType());
    }

    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return txtBox.addChangeHandler(handler);
    }

    @Override
    public HandlerRegistration addDragEndHandler(DragEndHandler handler) {
        return txtBox.addDragEndHandler(handler);
    }

    @Override
    public HandlerRegistration addDragEnterHandler(DragEnterHandler handler) {
        return txtBox.addDragEnterHandler(handler);
    }

    @Override
    public HandlerRegistration addDragLeaveHandler(DragLeaveHandler handler) {
        return txtBox.addDragLeaveHandler(handler);
    }

    @Override
    public HandlerRegistration addDragHandler(DragHandler handler) {
        return txtBox.addDragHandler(handler);
    }

    @Override
    public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
        return txtBox.addDragOverHandler(handler);
    }

    @Override
    public HandlerRegistration addDragStartHandler(DragStartHandler handler) {
        return txtBox.addDragStartHandler(handler);
    }

    @Override
    public HandlerRegistration addDropHandler(DropHandler handler) {
        return txtBox.addDropHandler(handler);
    }

    @Override
    public HandlerRegistration addFocusHandler(FocusHandler handler) {
        return txtBox.addFocusHandler(handler);
    }

    @Override
    public HandlerRegistration addBlurHandler(BlurHandler handler) {
        return txtBox.addBlurHandler(handler);
    }

    @Override
    public HandlerRegistration addGestureStartHandler(GestureStartHandler handler) {
        return txtBox.addGestureStartHandler(handler);
    }

    @Override
    public HandlerRegistration addGestureChangeHandler(GestureChangeHandler handler) {
        return txtBox.addGestureChangeHandler(handler);
    }

    @Override
    public HandlerRegistration addGestureEndHandler(GestureEndHandler handler) {
        return txtBox.addGestureEndHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return txtBox.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return txtBox.addKeyPressHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
        return txtBox.addMouseDownHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
        return txtBox.addMouseUpHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
        return txtBox.addMouseOutHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
        return txtBox.addMouseOverHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
        return txtBox.addMouseMoveHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
        return txtBox.addMouseWheelHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
        return txtBox.addTouchStartHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
        return txtBox.addTouchMoveHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
        return txtBox.addTouchEndHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
        return txtBox.addTouchCancelHandler(handler);
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
        return txtBox.addDoubleClickHandler(handler);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return txtBox.addClickHandler(handler);
    }

    @Override
    public void setError(String error) {
        errorMixin.setError(error);

        removeErrorModifiers();
        lblName.setStyleName("red-text");
        txtBox.getElement().addClassName("invalid");
        isValid = false;
    }

    @Override
    public void setSuccess(String success) {
        errorMixin.setSuccess(success);

        removeErrorModifiers();
        lblName.setStyleName("green-text");
        txtBox.getElement().addClassName("valid");
        isValid = true;
    }

    @Override
    public void clearErrorOrSuccess() {
        errorMixin.clearErrorOrSuccess();
        removeErrorModifiers();
    }

    @Override
    public MaterialIcon getIcon() {
        return icon;
    }

    @Override
    public void setIconType(IconType iconType) {
        icon.setIconType(iconType);
        icon.setIconPrefix(true);
    }

    @Override
    public void setIconPosition(IconPosition position) {
        icon.setIconPosition(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        icon.setIconSize(size);
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        icon.setIconFontSize(size, unit);
    }

    @Override
    public void setIconColor(String iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        icon.setIconPrefix(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return icon.isIconPrefix();
    }

    @Override
    public void setLength(int length) {
        counterMixin.setLength(length);
    }

    @Override
    public int getLength() {
        return counterMixin.getLength();
    }

    @Ignore
    public TextBox getTxtBox() {
        return txtBox;
    }

    public void showErrors(List<EditorError> errors) {
        if(errors.isEmpty()) {
            setSuccess("");
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (EditorError error : errors) {
                if (error.getEditor().equals(this)) {
                    sb.append("\n").append(error.getMessage());
                }
            }

            if (sb.length() == 0) {
                setSuccess("");
                return;
            }
            setError(sb.substring(1));
        }
    }
}
