package gwt.material.design.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

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
import com.google.gwt.event.logical.shared.ValueChangeEvent;
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
    @Ignore protected ValueBoxBase<T> valueBoxBase;
    private ValueBoxEditor<T> editor;
    private MaterialIcon icon = new MaterialIcon();
    private CounterMixin<MaterialValueBox<T>> counterMixin = new CounterMixin<>(this);

    public class MaterialValueBoxEditor<V> extends ValueBoxEditor<V> {
        private final ValueBoxBase<V> valueBoxBase;

        private MaterialValueBoxEditor(ValueBoxBase<V> valueBoxBase) {
            super(valueBoxBase);
            this.valueBoxBase = valueBoxBase;
        }

        @Override
        public void setValue(V value) {
            super.setValue(value);
            if (this.valueBoxBase.getText() != null && !this.valueBoxBase.getText().isEmpty()) {
                label.addStyleName("active");
            } else {
                label.removeStyleName("active");
            }
        }
    }

    private final ErrorMixin<MaterialValueBox<T>, MaterialLabel> errorMixin = new ErrorMixin<>(this, lblError, valueBoxBase);

    public MaterialValueBox() {
        super(Document.get().createDivElement(), "input-field");
    }

    public MaterialValueBox(ValueBoxBase<T> tValueBox) {
        this();
        initValueBox(tValueBox);
    }

    public void initValueBox(ValueBoxBase<T> tValueBox) {
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
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> handler) {
        return valueBoxBase.addValueChangeHandler(new ValueChangeHandler<T>() {
            @Override
            public void onValueChange(ValueChangeEvent<T> event) {
                if(isEnabled()){
                    handler.onValueChange(event);
                }
            }
        });
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
            editor = new MaterialValueBoxEditor<>(valueBoxBase);
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
    public HandlerRegistration addKeyUpHandler(final KeyUpHandler handler) {
        return addDomHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if(isEnabled()){
                    handler.onKeyUp(event);
                }
            }
        }, KeyUpEvent.getType());
    }

    @Override
    public HandlerRegistration addChangeHandler(final ChangeHandler handler) {
        return valueBoxBase.addChangeHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent event) {
                if(isEnabled()){
                    handler.onChange(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addDragEndHandler(final DragEndHandler handler) {
        return valueBoxBase.addDragEndHandler(new DragEndHandler() {
            @Override
            public void onDragEnd(DragEndEvent event) {
                if(isEnabled()){
                    handler.onDragEnd(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addDragEnterHandler(final DragEnterHandler handler) {
        return valueBoxBase.addDragEnterHandler(new DragEnterHandler() {
            @Override
            public void onDragEnter(DragEnterEvent event) {
                if(isEnabled()){
                    handler.onDragEnter(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addDragLeaveHandler(final DragLeaveHandler handler) {
        return valueBoxBase.addDragLeaveHandler(new DragLeaveHandler() {
            @Override
            public void onDragLeave(DragLeaveEvent event) {
                if(isEnabled()) {
                    handler.onDragLeave(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addDragHandler(final DragHandler handler) {
        return valueBoxBase.addDragHandler(new DragHandler() {
            @Override
            public void onDrag(final DragEvent event) {
                if(isEnabled()){
                    handler.onDrag(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addDragOverHandler(final DragOverHandler handler) {
        return valueBoxBase.addDragOverHandler(new DragOverHandler() {
            @Override
            public void onDragOver(DragOverEvent event) {
                if(isEnabled()){
                    handler.onDragOver(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addDragStartHandler(final DragStartHandler handler) {
        return valueBoxBase.addDragStartHandler(new DragStartHandler() {
            @Override
            public void onDragStart(DragStartEvent event) {
                if(isEnabled()){
                    handler.onDragStart(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addDropHandler(final DropHandler handler) {
        return valueBoxBase.addDropHandler(new DropHandler() {
            @Override
            public void onDrop(DropEvent event) {
                if(isEnabled()){
                    handler.onDrop(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addFocusHandler(final FocusHandler handler) {
        return valueBoxBase.addFocusHandler(new FocusHandler() {
            @Override
            public void onFocus(FocusEvent event) {
                if(isEnabled()){
                    handler.onFocus(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addBlurHandler(final BlurHandler handler) {
        return valueBoxBase.addBlurHandler(new BlurHandler() {
            @Override
            public void onBlur(BlurEvent event) {
                if(isEnabled()){
                    handler.onBlur(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addGestureStartHandler(final GestureStartHandler handler) {
        return valueBoxBase.addGestureStartHandler(new GestureStartHandler() {
            @Override
            public void onGestureStart(GestureStartEvent event) {
                if(isEnabled()){
                    handler.onGestureStart(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addGestureChangeHandler(final GestureChangeHandler handler) {
        return valueBoxBase.addGestureChangeHandler(new GestureChangeHandler() {
            @Override
            public void onGestureChange(GestureChangeEvent event) {
                if(isEnabled()){
                    handler.onGestureChange(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addGestureEndHandler(final GestureEndHandler handler) {
        return valueBoxBase.addGestureEndHandler(new GestureEndHandler() {
            @Override
            public void onGestureEnd(GestureEndEvent event) {
                if(isEnabled()){
                    handler.onGestureEnd(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addKeyDownHandler(final KeyDownHandler handler) {
        return valueBoxBase.addKeyDownHandler(new KeyDownHandler() {
            @Override
            public void onKeyDown(KeyDownEvent event) {
                if(isEnabled()){
                    handler.onKeyDown(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addKeyPressHandler(final KeyPressHandler handler) {
        return valueBoxBase.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent event) {
                if(isEnabled()){
                    handler.onKeyPress(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
        return valueBoxBase.addMouseDownHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                if(isEnabled()){
                    handler.onMouseDown(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
        return valueBoxBase.addMouseUpHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                if(isEnabled()){
                    handler.onMouseUp(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
        return valueBoxBase.addMouseOutHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                if(isEnabled()){
                    handler.onMouseOut(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
        return valueBoxBase.addMouseOverHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                if(isEnabled()){
                    handler.onMouseOver(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
        return valueBoxBase.addMouseMoveHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent event) {
                if(isEnabled()){
                    handler.onMouseMove(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
        return valueBoxBase.addMouseWheelHandler(new MouseWheelHandler() {
            @Override
            public void onMouseWheel(MouseWheelEvent event) {
                if(isEnabled()){
                    handler.onMouseWheel(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addTouchStartHandler(final TouchStartHandler handler) {
        return valueBoxBase.addTouchStartHandler(new TouchStartHandler() {
            @Override
            public void onTouchStart(TouchStartEvent event) {
                if(isEnabled()){
                    handler.onTouchStart(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addTouchMoveHandler(final TouchMoveHandler handler) {
        return valueBoxBase.addTouchMoveHandler(new TouchMoveHandler() {
            @Override
            public void onTouchMove(TouchMoveEvent event) {
                if(isEnabled()){
                    handler.onTouchMove(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addTouchEndHandler(final TouchEndHandler handler) {
        return valueBoxBase.addTouchEndHandler(new TouchEndHandler() {
            @Override
            public void onTouchEnd(TouchEndEvent event) {
                if(isEnabled()){
                    handler.onTouchEnd(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addTouchCancelHandler(final TouchCancelHandler handler) {
        return valueBoxBase.addTouchCancelHandler(new TouchCancelHandler() {
            @Override
            public void onTouchCancel(TouchCancelEvent event) {
                if(isEnabled()){
                    handler.onTouchCancel(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
        return valueBoxBase.addDoubleClickHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                if(isEnabled()){
                    handler.onDoubleClick(event);
                }
            }
        });
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return valueBoxBase.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if(isEnabled()){
                    handler.onClick(event);
                }
            }
        });
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
    public void setFocus(final boolean focused) {
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
            @Override
            public void execute() {
                valueBoxBase.setFocus(focused);
                if (focused) {
                    label.addStyleName("active");
                } else {
                    updateLabelActiveStyle();
                }
            }
        });
    }

    /**
     * Updates the style of the field label according to the field value if the
     * field value is empty - null or "" - removes the label 'active' style else
     * will add the 'active' style to the field label.
     */
    private void updateLabelActiveStyle() {
        if (this.valueBoxBase.getText() != null && !this.valueBoxBase.getText().isEmpty()) {
            label.addStyleName("active");
        } else {
            label.removeStyleName("active");
        }
    }

    @Override
    public void setTabIndex(int tabIndex) {
        valueBoxBase.setTabIndex(tabIndex);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        valueBoxBase.setEnabled(enabled);
    }
}
