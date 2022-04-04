/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
package gwt.material.design.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.AutoDirectionHandler;
import com.google.gwt.i18n.shared.DirectionEstimator;
import com.google.gwt.i18n.shared.HasDirectionEstimator;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ValueBoxBase;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.*;
import gwt.material.design.client.constants.*;
import gwt.material.design.client.events.DragEndEvent;
import gwt.material.design.client.events.DragEnterEvent;
import gwt.material.design.client.events.DragLeaveEvent;
import gwt.material.design.client.events.DragOverEvent;
import gwt.material.design.client.events.DragStartEvent;
import gwt.material.design.client.events.DropEvent;
import gwt.material.design.client.events.*;
import gwt.material.design.client.ui.html.Label;

import static gwt.material.design.jquery.client.api.JQuery.$;

//@formatter:off

/**
 * MaterialValueBox is an input field that accepts any text based string from user.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code <m:MaterialTextBox placeholder="First Name" />}
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @author paulux84
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#textfields">Material TextBox</a>
 * @see <a href="https://material.io/guidelines/components/text-fields.html#">Material Design Specification</a>
 */
//@formatter:on
public class MaterialValueBox<T> extends AbstractValueWidget<T> implements HasChangeHandlers, HasName,
    HasDirectionEstimator, HasText, AutoDirectionHandler.Target, IsEditor<ValueBoxEditor<T>>, HasIcon,
    HasInputType, HasPlaceholder, HasCounter, HasReadOnly, HasActive, HasFieldTypes,
    HasToggleReadOnlyHandler, HasAutocomplete, HasPasteHandlers, HasFieldSensitivity, HasLabel, HasRegex {

    private boolean returnBlankAsNull;
    private InputType type = InputType.TEXT;
    private ValueBoxEditor<T> editor;
    private Label fieldLabel = new Label();
    private MaterialLabel errorLabel = new MaterialLabel();
    private MaterialIcon icon = new MaterialIcon();

    @Editor.Ignore
    protected ValueBoxBase<T> valueBoxBase;

    private CounterMixin<MaterialValueBox<T>> counterMixin;
    private StatusTextMixin<AbstractValueWidget, MaterialLabel> statusTextMixin;
    private ReadOnlyMixin<MaterialValueBox, ValueBoxBase> readOnlyMixin;
    private FocusableMixin<MaterialWidget> focusableMixin;
    private ActiveMixin<MaterialValueBox> activeMixin;
    private FieldTypeMixin<MaterialValueBox> fieldTypeMixin;
    private FieldSensitivityMixin<MaterialValueBox> fieldSensitivityMixin;
    private RegexMixin<MaterialValueBox> regexMixin;
    private FieldClearMixin<MaterialValueBox> clearMixin;

    public class MaterialValueBoxEditor<V> extends ValueBoxEditor<V> {
        private final ValueBoxBase<V> valueBoxBase;

        private MaterialValueBoxEditor(ValueBoxBase<V> valueBoxBase) {
            super(valueBoxBase);
            this.valueBoxBase = valueBoxBase;
        }

        @Override
        public void setValue(V value) {
            super.setValue(value);
            if (valueBoxBase.getText() != null && !valueBoxBase.getText().isEmpty()) {
                fieldLabel.addStyleName(CssName.ACTIVE);
            } else {
                fieldLabel.removeStyleName(CssName.ACTIVE);
            }
        }
    }

    protected MaterialValueBox() {
        super(Document.get().createDivElement(), CssName.INPUT_FIELD);
    }

    public MaterialValueBox(ValueBoxBase<T> tValueBox) {
        this();
        setup(tValueBox);
    }

    public void setup(ValueBoxBase<T> tValueBox) {
        valueBoxBase = tValueBox;
        add(valueBoxBase);
        setAutocomplete(Autocomplete.OFF);
    }

    @Deprecated
    @UiChild(limit = 1)
    public void addValueBox(ValueBoxBase<T> widget) {
        setup(widget);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        String id = DOM.createUniqueId();
        valueBoxBase.getElement().setId(id);
        fieldLabel.getElement().setAttribute("for", id);
        loadEvents();
        // Make valueBoxBase the primary focus target
        getFocusableMixin().setUiObject(new MaterialWidget(valueBoxBase.getElement()));
        getClearMixin().load();
    }

    @Override
    protected void onUnload() {
        super.onUnload();

        unloadEvents();
    }

    @Override
    public void reset() {
        super.reset();

        clear();
    }

    public void setEnableClear(boolean enableClear) {
        getClearMixin().setEnableClear(enableClear);
    }

    /**
     * Resets the text box by removing its content and resetting visual state.
     */
    public void clear() {
        valueBoxBase.setText("");
        clearStatusText();

        if (getPlaceholder() == null || getPlaceholder().isEmpty()) {
            fieldLabel.removeStyleName(CssName.ACTIVE);
        }

        getClearMixin().reset();
    }

    public void loadEvents() {
        if (valueBoxBase != null && valueBoxBase.isAttached()) {
            $("#" + valueBoxBase.getElement().getId()).on("paste", event -> {
                PasteNativeEvent nativeEvent = (PasteNativeEvent) event;
                if (nativeEvent != null && nativeEvent.originalEvent.clipboardData != null) {
                    PasteEvent.fire(this, nativeEvent.originalEvent.clipboardData.getData("text/plain"));
                }
                return true;
            });
        }
    }

    public void unloadEvents() {
        if (valueBoxBase != null) {
            $("#" + valueBoxBase.getElement().getId()).off("paste");
        }
    }

    public void select() {
        if (valueBoxBase != null) {
            $("#" + valueBoxBase.getElement().getId()).select();
        }
    }

    public void removeErrorModifiers() {
        valueBoxBase.getElement().removeClassName(CssName.VALID);
        valueBoxBase.getElement().removeClassName(CssName.INVALID);
        removeStyleName(CssName.INVALID);
        removeStyleName(CssName.VALID);
    }

    @Override
    public String getText() {
        return valueBoxBase.getText();
    }

    @Override
    public void setText(String text) {
        valueBoxBase.setText(text);

        if (text != null && !text.isEmpty()) {
            fieldLabel.addStyleName(CssName.ACTIVE);
        }
    }

    @Override
    public void setLabel(String label) {
        this.fieldLabel.setText(label);

        if (!getPlaceholder().isEmpty()) {
            this.fieldLabel.setStyleName(CssName.ACTIVE);
        }
    }

    @Override
    public String getLabel() {
        return fieldLabel.getText();
    }

    public Label getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(Label fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    @Override
    public String getPlaceholder() {
        return valueBoxBase.getElement().getAttribute("placeholder");
    }

    @Override
    public void setPlaceholder(String placeholder) {
        valueBoxBase.getElement().setAttribute("placeholder", placeholder);

        if (fieldLabel.getText() != null && !fieldLabel.getText().isEmpty()) {
            fieldLabel.setStyleName(CssName.ACTIVE);
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
        if (getType() != InputType.SEARCH) {
            add(fieldLabel);
            errorLabel.setVisible(false);
            add(errorLabel);
        }
    }

    @Override
    public T getValue() {
        if (isReturnBlankAsNull() && isBlank()) {
            return null;
        }
        return valueBoxBase.getValue();
    }

    /**
     * @return
     * @see #setReturnBlankAsNull(boolean)
     */
    public boolean isReturnBlankAsNull() {
        return this.returnBlankAsNull;
    }

    /**
     * <p>With this attribute set to <code>true</code> a call to {@link #getValue()} returns <code>null</code> when
     * the validator obtained by {@link #createBlankValidator()} recognizes the value as blank.
     * </p>
     * <p>
     * You can use this attribute for non-mandatory input fields that have @Pattern constraint, like an e-mail or phone input.
     * This way you avoid the @Pattern#regexp being matched against an empty string when your user does not enter
     * a value into such input.
     * </p>
     *
     * @param returnBlankAsNull should {@link #getValue()} return <code>null</code> instead of a blank value?
     */
    public void setReturnBlankAsNull(boolean returnBlankAsNull) {
        this.returnBlankAsNull = returnBlankAsNull;
    }

    protected boolean isBlank() {
        return !createBlankValidator().isValid(valueBoxBase.getValue());
    }

    @Override
    public void setValue(T value, boolean fireEvents) {
        valueBoxBase.setValue(value, fireEvents);

        if (value != null && !value.toString().isEmpty()) {
            fieldLabel.addStyleName(CssName.ACTIVE);
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
    public void setErrorText(String errorText) {
        super.setErrorText(errorText);
        removeErrorModifiers();
        valueBoxBase.getElement().addClassName(CssName.INVALID);
        addStyleName(CssName.INVALID);
    }

    @Override
    public void setSuccessText(String successText) {
        super.setSuccessText(successText);
        removeErrorModifiers();
        valueBoxBase.getElement().addClassName(CssName.VALID);
        addStyleName(CssName.VALID);
    }

    @Override
    public void setHelperText(String helperText) {
        super.setHelperText(helperText);
        removeErrorModifiers();
    }

    @Override
    public void clearStatusText() {
        super.clearStatusText();
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
        errorLabel.setPaddingLeft(44);
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
    public void setIconColor(Color iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public Color getIconColor() {
        return icon.getIconColor();
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
    public void setIconDisplay(IconDisplay iconDisplay) {
        icon.setIconDisplay(iconDisplay);
    }

    @Override
    public IconDisplay getIconDisplay() {
        return icon.getIconDisplay();
    }

    @Override
    public void setCustomIconType(String iconType) {
        icon.setCustomIconType(iconType);
    }

    @Override
    public String getCustomIconType() {
        return icon.getCustomIconType();
    }

    @Override
    public void setLength(int length) {
        getCounterMixin().setLength(length);
    }

    @Override
    public int getLength() {
        return getCounterMixin().getLength();
    }

    @Editor.Ignore
    public ValueBoxBase<T> asValueBoxBase() {
        return valueBoxBase;
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
        Scheduler.get().scheduleDeferred(() -> {
            valueBoxBase.setFocus(focused);
            if (focused) {
                fieldLabel.addStyleName(CssName.ACTIVE);
            } else {
                updateLabelActiveStyle();
            }
        });
    }

    /**
     * Updates the style of the field label according to the field value if the
     * field value is empty - null or "" - removes the label 'active' style else
     * will add the 'active' style to the field label.
     */
    protected void updateLabelActiveStyle() {
        if (this.valueBoxBase.getText() != null && !this.valueBoxBase.getText().isEmpty()) {
            fieldLabel.addStyleName(CssName.ACTIVE);
        } else {
            fieldLabel.removeStyleName(CssName.ACTIVE);
        }
    }

    public String getSelectedText() {
        return valueBoxBase.getSelectedText();
    }

    public int getSelectionLength() {
        return valueBoxBase.getSelectionLength();
    }

    public void setSelectionRange(int pos, int length) {
        valueBoxBase.setSelectionRange(pos, length);
    }

    @Override
    public void setActive(boolean active) {
        getActiveMixin().setActive(active);
    }

    @Override
    public boolean isActive() {
        return getActiveMixin().isActive();
    }

    @Override
    public void setReadOnly(boolean value) {
        getReadOnlyMixin().setReadOnly(value);
    }

    @Override
    public boolean isReadOnly() {
        return getReadOnlyMixin().isReadOnly();
    }

    @Override
    public void setToggleReadOnly(boolean toggle) {
        getReadOnlyMixin().setToggleReadOnly(toggle);
    }

    @Override
    public boolean isToggleReadOnly() {
        return getReadOnlyMixin().isToggleReadOnly();
    }

    public void setCursorPos(int pos) {
        valueBoxBase.setCursorPos(pos);
    }

    public void setAlignment(TextAlignment align) {
        valueBoxBase.setAlignment(align);
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

    @Override
    public boolean isEnabled() {
        return valueBoxBase.isEnabled();
    }

    @Override
    public void setFieldType(FieldType type) {
        getFieldTypeMixin().setFieldType(type);
    }

    @Override
    public FieldType getFieldType() {
        return getFieldTypeMixin().getFieldType();
    }

    @Override
    public void setLabelWidth(double percentWidth) {
        getFieldTypeMixin().setLabelWidth(percentWidth);
    }

    @Override
    public void setFieldWidth(double percentWidth) {
        getFieldTypeMixin().setFieldWidth(percentWidth);
    }

    @Override
    public void setAutocomplete(Autocomplete value) {
        valueBoxBase.getElement().setAttribute("autocomplete", value.getName());
    }

    @Override
    public Autocomplete getAutocomplete() {
        return Autocomplete.get(valueBoxBase.getElement().getAttribute("autocomplete"));
    }

    @Override
    public void setSensitive(boolean sensitive) {
        getFieldSensitivityMixin().setSensitive(sensitive);
    }

    @Override
    public void setSensitive(boolean sensitive, boolean fireEvents) {
        getFieldSensitivityMixin().setSensitive(sensitive, fireEvents);
    }

    @Override
    public boolean isSensitive() {
        return getFieldSensitivityMixin().isSensitive();
    }

    @Override
    public void setRegex(String regex) {
        getRegexMixin().setRegex(regex);
    }

    @Override
    public void setRegex(String regex, String replaceRegex) {
        getRegexMixin().setRegex(regex, replaceRegex);
    }

    @Override
    public String getRegex() {
        return getRegexMixin().getRegex();
    }

    @Ignore
    public ValueBoxBase<T> getValueBoxBase() {
        return valueBoxBase;
    }

    public Label getLabelWidget() {
        return fieldLabel;
    }

    public MaterialLabel getErrorLabel() {
        return errorLabel;
    }

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<T> handler) {
        return valueBoxBase.addValueChangeHandler(event -> {
            if (isEnabled()) {
                handler.onValueChange(event);
            }
        });
    }

    @Override
    public HandlerRegistration addDragStartHandler(DragStartEvent.DragStartHandler handler) {
        return valueBoxBase.addHandler(event -> {
            if (isEnabled()) {
                handler.onDragStart(event);
            }
        }, DragStartEvent.getType());
    }

    @Override
    public HandlerRegistration addDragMoveHandler(DragMoveEvent.DragMoveHandler handler) {
        return valueBoxBase.addHandler(event -> {
            if (isEnabled()) {
                handler.onDragMove(event);
            }
        }, DragMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addDragEndHandler(DragEndEvent.DragEndHandler handler) {
        return valueBoxBase.addHandler(event -> {
            if (isEnabled()) {
                handler.onDragEnd(event);
            }
        }, DragEndEvent.getType());
    }

    @Override
    public HandlerRegistration addDropActivateHandler(DropActivateEvent.DropActivateHandler handler) {
        return valueBoxBase.addHandler(event -> {
            if (isEnabled()) {
                handler.onDropActivate(event);
            }
        }, DropActivateEvent.getType());
    }

    @Override
    public HandlerRegistration addDragEnterHandler(DragEnterEvent.DragEnterHandler handler) {
        return valueBoxBase.addHandler(event -> {
            if (isEnabled()) {
                handler.onDragEnter(event);
            }
        }, DragEnterEvent.getType());
    }

    @Override
    public HandlerRegistration addDragLeaveHandler(DragLeaveEvent.DragLeaveHandler handler) {
        return valueBoxBase.addHandler(event -> {
            if (isEnabled()) {
                handler.onDragLeave(event);
            }
        }, DragLeaveEvent.getType());
    }

    @Override
    public HandlerRegistration addDragOverHandler(DragOverEvent.DragOverHandler handler) {
        return valueBoxBase.addHandler(event -> {
            if (isEnabled()) {
                handler.onDragOver(event);
            }
        }, DragOverEvent.getType());
    }

    @Override
    public HandlerRegistration addDropDeactivateHandler(DropDeactivateEvent.DropDeactivateHandler handler) {
        return valueBoxBase.addHandler(event -> {
            if (isEnabled()) {
                handler.onDropDeactivate(event);
            }
        }, DropDeactivateEvent.getType());
    }

    @Override
    public HandlerRegistration addDropHandler(DropEvent.DropHandler handler) {
        return valueBoxBase.addHandler(event -> {
            if (isEnabled()) {
                handler.onDrop(event);
            }
        }, DropEvent.getType());
    }

    @Override
    public HandlerRegistration addKeyUpHandler(final KeyUpHandler handler) {
        return valueBoxBase.addDomHandler(event -> {
            if (isEnabled()) {
                handler.onKeyUp(event);
            }
        }, KeyUpEvent.getType());
    }

    @Override
    public HandlerRegistration addChangeHandler(final ChangeHandler handler) {
        return valueBoxBase.addChangeHandler(event -> {
            if (isEnabled()) {
                handler.onChange(event);
            }
        });
    }

    @Override
    public HandlerRegistration addFocusHandler(final FocusHandler handler) {
        return valueBoxBase.addFocusHandler(event -> {
            if (isEnabled()) {
                handler.onFocus(event);
            }
        });
    }

    @Override
    public HandlerRegistration addBlurHandler(final BlurHandler handler) {
        return valueBoxBase.addBlurHandler(event -> {
            if (isEnabled()) {
                handler.onBlur(event);
            }
        });
    }

    @Override
    public HandlerRegistration addGestureStartHandler(final GestureStartHandler handler) {
        return valueBoxBase.addGestureStartHandler(event -> {
            if (isEnabled()) {
                handler.onGestureStart(event);
            }
        });
    }

    @Override
    public HandlerRegistration addGestureChangeHandler(final GestureChangeHandler handler) {
        return valueBoxBase.addGestureChangeHandler(event -> {
            if (isEnabled()) {
                handler.onGestureChange(event);
            }
        });
    }

    @Override
    public HandlerRegistration addGestureEndHandler(final GestureEndHandler handler) {
        return valueBoxBase.addGestureEndHandler(event -> {
            if (isEnabled()) {
                handler.onGestureEnd(event);
            }
        });
    }

    @Override
    public HandlerRegistration addKeyDownHandler(final KeyDownHandler handler) {
        return valueBoxBase.addKeyDownHandler(event -> {
            if (isEnabled()) {
                handler.onKeyDown(event);
            }
        });
    }

    @Override
    public HandlerRegistration addKeyPressHandler(final KeyPressHandler handler) {
        return valueBoxBase.addKeyPressHandler(event -> {
            if (isEnabled()) {
                handler.onKeyPress(event);
            }
        });
    }

    @Override
    public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
        return valueBoxBase.addMouseDownHandler(event -> {
            if (isEnabled()) {
                handler.onMouseDown(event);
            }
        });
    }

    @Override
    public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
        return valueBoxBase.addMouseUpHandler(event -> {
            if (isEnabled()) {
                handler.onMouseUp(event);
            }
        });
    }

    @Override
    public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
        return valueBoxBase.addMouseOutHandler(event -> {
            if (isEnabled()) {
                handler.onMouseOut(event);
            }
        });
    }

    @Override
    public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
        return valueBoxBase.addMouseOverHandler(event -> {
            if (isEnabled()) {
                handler.onMouseOver(event);
            }
        });
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
        return valueBoxBase.addMouseMoveHandler(event -> {
            if (isEnabled()) {
                handler.onMouseMove(event);
            }
        });
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
        return valueBoxBase.addMouseWheelHandler(event -> {
            if (isEnabled()) {
                handler.onMouseWheel(event);
            }
        });
    }

    @Override
    public HandlerRegistration addTouchStartHandler(final TouchStartHandler handler) {
        return valueBoxBase.addTouchStartHandler(event -> {
            if (isEnabled()) {
                handler.onTouchStart(event);
            }
        });
    }

    @Override
    public HandlerRegistration addTouchMoveHandler(final TouchMoveHandler handler) {
        return valueBoxBase.addTouchMoveHandler(event -> {
            if (isEnabled()) {
                handler.onTouchMove(event);
            }
        });
    }

    @Override
    public HandlerRegistration addTouchEndHandler(final TouchEndHandler handler) {
        return valueBoxBase.addTouchEndHandler(event -> {
            if (isEnabled()) {
                handler.onTouchEnd(event);
            }
        });
    }

    @Override
    public HandlerRegistration addTouchCancelHandler(final TouchCancelHandler handler) {
        return valueBoxBase.addTouchCancelHandler(event -> {
            if (isEnabled()) {
                handler.onTouchCancel(event);
            }
        });
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
        return valueBoxBase.addDoubleClickHandler(event -> {
            if (isEnabled()) {
                handler.onDoubleClick(event);
            }
        });
    }

    @Override
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return valueBoxBase.addClickHandler(event -> {
            if (isEnabled()) {
                handler.onClick(event);
            }
        });
    }

    @Override
    public HandlerRegistration addPasteHandler(PasteEvent.PasteEventHandler handler) {
        return addHandler(handler, PasteEvent.getType());
    }

    @Override
    public HandlerRegistration addToggleReadOnlyHandler(ToggleReadOnlyEvent.ToggleReadOnlyHandler handler) {
        return addHandler(handler, ToggleReadOnlyEvent.getType());
    }

    @Override
    public HandlerRegistration addRegexValidationHandler(RegexValidationEvent.RegexValidationHandler handler) {
        return addHandler(handler, RegexValidationEvent.getType());
    }

    @Override
    public HandlerRegistration addSensitivityChangedHandler(SensitivityChangedEvent.SensitivityChangedHandler handler) {
        return addHandler(handler, SensitivityChangedEvent.TYPE);
    }

    @Override
    protected FocusableMixin<MaterialWidget> getFocusableMixin() {
        if (focusableMixin == null) {
            focusableMixin = new FocusableMixin<>(new MaterialWidget(valueBoxBase.getElement()));
        }
        return focusableMixin;
    }

    @Override
    protected StatusTextMixin<AbstractValueWidget, MaterialLabel> getStatusTextMixin() {
        if (statusTextMixin == null) {
            statusTextMixin = new StatusTextMixin<>(this, errorLabel, valueBoxBase, fieldLabel);
        }
        return statusTextMixin;
    }

    protected ReadOnlyMixin<MaterialValueBox, ValueBoxBase> getReadOnlyMixin() {
        if (readOnlyMixin == null) {
            readOnlyMixin = new ReadOnlyMixin<>(this, valueBoxBase, readOnly -> ToggleReadOnlyEvent.fire(this, readOnly));
        }
        return readOnlyMixin;
    }

    protected ActiveMixin<MaterialValueBox> getActiveMixin() {
        if (activeMixin == null) {
            activeMixin = new ActiveMixin<>(this, fieldLabel);
        }
        return activeMixin;
    }

    protected CounterMixin<MaterialValueBox<T>> getCounterMixin() {
        if (counterMixin == null) {
            counterMixin = new CounterMixin<>(this);
        }
        return counterMixin;
    }

    protected FieldTypeMixin<MaterialValueBox> getFieldTypeMixin() {
        if (fieldTypeMixin == null) {
            fieldTypeMixin = new FieldTypeMixin<>(this, fieldLabel, valueBoxBase, errorLabel);
        }
        return fieldTypeMixin;
    }

    protected FieldSensitivityMixin<MaterialValueBox> getFieldSensitivityMixin() {
        if (fieldSensitivityMixin == null) {
            fieldSensitivityMixin = new FieldSensitivityMixin(this);
        }
        return fieldSensitivityMixin;
    }

    protected RegexMixin<MaterialValueBox> getRegexMixin() {
        if (regexMixin == null) {
            regexMixin = new RegexMixin<>(this);
        }
        return regexMixin;
    }

    protected FieldClearMixin<MaterialValueBox> getClearMixin() {
        if (clearMixin == null) {
            clearMixin = new FieldClearMixin<>(this);
        }
        return clearMixin;
    }
}
