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

import gwt.material.design.client.base.HasCounter;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.base.HasInputType;
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.CounterMixin;
import gwt.material.design.client.base.mixin.ErrorMixin;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.html.Label;

import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.HasEditorErrors;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.DragEndHandler;
import com.google.gwt.event.dom.client.DragEnterHandler;
import com.google.gwt.event.dom.client.DragHandler;
import com.google.gwt.event.dom.client.DragLeaveHandler;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.GestureChangeHandler;
import com.google.gwt.event.dom.client.GestureEndHandler;
import com.google.gwt.event.dom.client.GestureStartHandler;
import com.google.gwt.event.dom.client.HasAllDragAndDropHandlers;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasAllGestureHandlers;
import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasAllTouchHandlers;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.dom.client.TouchCancelHandler;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.AutoDirectionHandler;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.ValueBoxBase;

//@formatter:off

/**
 * Material Text Box is an input field that accepts any text based string from user.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code <m:MaterialTextBox placeholder="First Name" />}
 * </pre>
 * @see <a href="http://gwt-material-demo.herokuapp.com/#forms">Material TextBox</a>
 * @author kevzlou7979
 * @author Ben Dol
 * @author paulux84
 */
//@formatter:on
public class MaterialValueBox<T> extends MaterialWidget implements HasChangeHandlers, HasName, HasDirectionEstimator,
        HasValue<T>, HasText, AutoDirectionHandler.Target, IsEditor<ValueBoxEditor<T>>, HasKeyUpHandlers,
        HasClickHandlers, HasDoubleClickHandlers, HasAllDragAndDropHandlers, HasAllFocusHandlers, HasIcon,
        HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers, HasAllTouchHandlers, HasError, HasInputType,
        HasPlaceholder, HasCounter, HasEditorErrors<T> {


    private String placeholder;
    private InputType type = InputType.TEXT;
    private boolean isValid = true;

    private MaterialLabel lblError = new MaterialLabel();

    private Label label = new Label();
    private MaterialLabel lblName = new MaterialLabel();
    @Ignore
    protected ValueBoxBase<T> valueBoxBase;
    private ValueBoxEditor<T> editor;
    private MaterialIcon icon = new MaterialIcon();
    private CounterMixin<MaterialValueBox<T>> counterMixin = new CounterMixin<>(this);

    public class MaterialValueBoxEditor<T> extends ValueBoxEditor<T>{

        private final ValueBoxBase<T> valueBoxBase;

        private MaterialValueBoxEditor(ValueBoxBase<T> valueBoxBase){
            super(valueBoxBase);
            this.valueBoxBase=valueBoxBase;
        }


        @Override
        public void setValue(T value) {
            super.setValue(value);
            if (this.valueBoxBase.getText() != null && !this.valueBoxBase.getText().isEmpty()) {
                label.addStyleName("active");
            }else
                label.removeStyleName("active");
        }

    }

    private final ErrorMixin<MaterialValueBox<T>, MaterialLabel> errorMixin = new ErrorMixin<>(this, lblError, valueBoxBase);

    public MaterialValueBox(ValueBoxBase<T> tValueBox) {
        super(Document.get().createDivElement());
        setStyleName("input-field");
        initValueBox(tValueBox);
    }

    private void initValueBox(ValueBoxBase<T> tValueBox) {
        valueBoxBase = tValueBox;
        add(valueBoxBase);
    }

    @Deprecated
    @UiChild(limit = 1)
    public void addValueBox(ValueBoxBase<T> widget) {
        initValueBox(widget);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        String id = DOM.createUniqueId();
        valueBoxBase.getElement().setId(id);
        label.getElement().setAttribute("for", id);
    }

    /**
     * Resets the textbox by removing its content and resetting visual state.
     */
    public void clear() {
        valueBoxBase.setText("");
        clearErrorOrSuccess();
        label.removeStyleName("active");
    }

    public void removeErrorModifiers() {
        valueBoxBase.getElement().removeClassName("valid");
        valueBoxBase.getElement().removeClassName("invalid");
        lblName.removeStyleName("green-text");
        lblName.removeStyleName("red-text");
    }

    @Override
    public String getText() {
        return valueBoxBase.getText();
    }

    @Override
    public void setText(String text) {
        valueBoxBase.setText(text);

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
        if(getType() != InputType.SEARCH) {
            lblName.setText(placeholder);
        }else{
            valueBoxBase.getElement().setAttribute("placeholder", placeholder);
        }
    }

    @Override
    public InputType getType() {
        return type;
    }

    @Override
    public void setType(InputType type) {
        this.type = type;
        valueBoxBase.getElement().setAttribute("type", type.getType());
        if(getType() != InputType.SEARCH) {
            valueBoxBase.setStyleName("validate");
            add(label);
            label.add(lblName);
            lblError.setVisible(false);
            add(lblError);
        }
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
        return valueBoxBase.addValueChangeHandler(handler);
    }

    @Override
    public T getValue() {
        return valueBoxBase.getValue();
    }

    @Override
    public void setValue(T value) {
        setValue(value, false);
    }

    @Override
    public void setValue(T value, boolean fireEvents) {
        valueBoxBase.setValue(value, fireEvents);

        if (value != null && !value.toString().isEmpty()) {
            label.addStyleName("active");
        }
    }

    @Override
    public void setDirection(Direction direction) {
        valueBoxBase.setDirection(direction);
    }

    @Override
    public Direction getDirection() {
        return valueBoxBase.getDirection();
    }

    @Override
    public ValueBoxEditor<T> asEditor() {
        if (editor == null) {
            editor = new MaterialValueBoxEditor(valueBoxBase);
        }
        return editor;
    }

    @Override
    public DirectionEstimator getDirectionEstimator() {
        return valueBoxBase.getDirectionEstimator();
    }

    @Override
    public void setDirectionEstimator(boolean enabled) {
        valueBoxBase.setDirectionEstimator(enabled);
    }

    @Override
    public void setDirectionEstimator(DirectionEstimator directionEstimator) {
        valueBoxBase.setDirectionEstimator(directionEstimator);
    }

    @Override
    public void setName(String name) {
        valueBoxBase.setName(name);
    }

    @Override
    public String getName() {
        return valueBoxBase.getName();
    }

    @Override
    public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
        return addDomHandler(handler, KeyUpEvent.getType());
    }

    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
        return valueBoxBase.addChangeHandler(handler);
    }

    @Override
    public HandlerRegistration addDragEndHandler(DragEndHandler handler) {
        return valueBoxBase.addDragEndHandler(handler);
    }

    @Override
    public HandlerRegistration addDragEnterHandler(DragEnterHandler handler) {
        return valueBoxBase.addDragEnterHandler(handler);
    }

    @Override
    public HandlerRegistration addDragLeaveHandler(DragLeaveHandler handler) {
        return valueBoxBase.addDragLeaveHandler(handler);
    }

    @Override
    public HandlerRegistration addDragHandler(DragHandler handler) {
        return valueBoxBase.addDragHandler(handler);
    }

    @Override
    public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
        return valueBoxBase.addDragOverHandler(handler);
    }

    @Override
    public HandlerRegistration addDragStartHandler(DragStartHandler handler) {
        return valueBoxBase.addDragStartHandler(handler);
    }

    @Override
    public HandlerRegistration addDropHandler(DropHandler handler) {
        return valueBoxBase.addDropHandler(handler);
    }

    @Override
    public HandlerRegistration addFocusHandler(FocusHandler handler) {
        return valueBoxBase.addFocusHandler(handler);
    }

    @Override
    public HandlerRegistration addBlurHandler(BlurHandler handler) {
        return valueBoxBase.addBlurHandler(handler);
    }

    @Override
    public HandlerRegistration addGestureStartHandler(GestureStartHandler handler) {
        return valueBoxBase.addGestureStartHandler(handler);
    }

    @Override
    public HandlerRegistration addGestureChangeHandler(GestureChangeHandler handler) {
        return valueBoxBase.addGestureChangeHandler(handler);
    }

    @Override
    public HandlerRegistration addGestureEndHandler(GestureEndHandler handler) {
        return valueBoxBase.addGestureEndHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
        return valueBoxBase.addKeyDownHandler(handler);
    }

    @Override
    public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
        return valueBoxBase.addKeyPressHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
        return valueBoxBase.addMouseDownHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
        return valueBoxBase.addMouseUpHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
        return valueBoxBase.addMouseOutHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
        return valueBoxBase.addMouseOverHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
        return valueBoxBase.addMouseMoveHandler(handler);
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
        return valueBoxBase.addMouseWheelHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
        return valueBoxBase.addTouchStartHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
        return valueBoxBase.addTouchMoveHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
        return valueBoxBase.addTouchEndHandler(handler);
    }

    @Override
    public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
        return valueBoxBase.addTouchCancelHandler(handler);
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
        return valueBoxBase.addDoubleClickHandler(handler);
    }

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return valueBoxBase.addClickHandler(handler);
    }

    @Override
    public void setError(String error) {
        errorMixin.setError(error);

        removeErrorModifiers();
        lblName.setStyleName("red-text");
        valueBoxBase.getElement().addClassName("invalid");
        isValid = false;
    }

    @Override
    public void setSuccess(String success) {
        errorMixin.setSuccess(success);

        removeErrorModifiers();
        lblName.setStyleName("green-text");
        valueBoxBase.getElement().addClassName("valid");
        isValid = true;
    }

    @Override
    public void clearErrorOrSuccess() {
        errorMixin.clearErrorOrSuccess();
        isValid = true;
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
        lblError.setPaddingLeft(44);
        insert(icon, 0);
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
    public ValueBoxBase<T> asGwtValueBoxBase() {
        return valueBoxBase;
    }

    public void showErrors(List<EditorError> errors) {
        if(errors == null || errors.isEmpty()) {
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

    @Override
    public int getTabIndex() {
        return valueBoxBase.getTabIndex();
    }

    @Override
    public void setAccessKey(char key) {
        valueBoxBase.setAccessKey(key);
    }

    @Override
    public void setFocus(boolean focused) {
        valueBoxBase.setFocus(focused);
    }

    @Override
    public void setTabIndex(int tabIndex) {
        valueBoxBase.setTabIndex(tabIndex);
    }
}
