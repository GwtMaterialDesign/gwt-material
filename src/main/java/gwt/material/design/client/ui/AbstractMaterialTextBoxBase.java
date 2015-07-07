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


import gwt.material.design.client.custom.CustomIcon;
import gwt.material.design.client.custom.CustomLabel;

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
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.Widget;

/**
 * Abstract base class for Material text box widgets.
 *
 * @author Anton Johansson
 */
abstract class AbstractMaterialTextBoxBase extends Composite implements
		HasChangeHandlers, HasName, HasDirectionEstimator, HasValue<String>,
		HasText, AutoDirectionHandler.Target, IsEditor<ValueBoxEditor<String>>,
		HasKeyUpHandlers, HasClickHandlers, HasDoubleClickHandlers,
		HasEnabled, HasAllDragAndDropHandlers, HasAllFocusHandlers,
		HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers,
		HasAllTouchHandlers
{
	private String type = "text";
	private String icon = "";
	private boolean isValid = true;
	private String length;
	
	protected AbstractMaterialTextBoxBase() {
		Widget widget = getContentWidget();
		initWidget(widget);
	}

	/**
	 * Gets the content widget to use for this text box.
	 * 
	 * @return Returns the content widget.
	 */
	abstract Widget getContentWidget();
	
	/**
	 * Gets the custom label.
	 */
	abstract CustomLabel customLabel();
	
	/**
	 * Gets the title label.
	 */
	abstract Label label();

	/**
	 * Gets the actual text box.
	 */
	abstract TextBoxBase textBox();

	/**
	 * Gets the icon panel.
	 */
	abstract CustomIcon iconPanel();
	
	/**
	 * Resets the textbox by removing its content and resetting visual state.
	 */
	public void clear() {
		textBox().setText("");
		backToDefault();
		customLabel().removeStyleName("active");
	}

	public void setInvalid() {
		backToDefault();
		textBox().getElement().addClassName("invalid");
		isValid = false;
	}

	public void setValid() {
		backToDefault();
		textBox().getElement().addClassName("valid");
		isValid = true;
	}

	public void backToDefault() {
		textBox().getElement().removeClassName("valid");
		textBox().getElement().removeClassName("invalid");
	}

	public String getPlaceholder() {
		return label().getText();
	}

	public void setPlaceholder(String placeholder) {
		label().setText(placeholder);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		textBox().getElement().setAttribute("type", type);
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		iconPanel().addStyleName(icon + " prefix");
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
		textBox().getElement().setAttribute("length", length);
	}
	
	@Override
	protected void onAttach() {
		super.onAttach();
		String name = String.valueOf(hashCode());
		textBox().getElement().setId(name);
		customLabel().getElement().setAttribute("for", name);
	}

	@Override
	public boolean isEnabled() {
		return textBox().isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		textBox().setEnabled(enabled);
	}

	@Override
	public String getText() {
		return textBox().getText();
	}

	@Override
	public void setText(String text) {
		textBox().setText(text);
		customLabel().addStyleName("active");
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
		return textBox().addValueChangeHandler(handler);
	}

	@Override
	public String getValue() {
		return textBox().getValue();
	}

	@Override
	public void setValue(String value) {
		textBox().setValue(value);
	}

	@Override
	public void setValue(String value, boolean fireEvents) {
		textBox().setValue(value, fireEvents);
	}

	@Override
	public void setDirection(Direction direction) {
		textBox().setDirection(direction);
	}

	@Override
	public Direction getDirection() {
		return textBox().getDirection();
	}

	@Override
	public ValueBoxEditor<String> asEditor() {
		return textBox().asEditor();
	}

	@Override
	public DirectionEstimator getDirectionEstimator() {
		return textBox().getDirectionEstimator();
	}

	@Override
	public void setDirectionEstimator(boolean enabled) {
		textBox().setDirectionEstimator(enabled);
	}

	@Override
	public void setDirectionEstimator(DirectionEstimator directionEstimator) {
		textBox().setDirectionEstimator(directionEstimator);
	}

	@Override
	public void setName(String name) {
		textBox().setName(name);
	}

	@Override
	public String getName() {
		return textBox().getName();
	}

	@Override
	public HandlerRegistration addChangeHandler(ChangeHandler handler) {
		return textBox().addChangeHandler(handler);
	}

	@Override
	public HandlerRegistration addDragEndHandler(DragEndHandler handler) {
		return textBox().addDragEndHandler(handler);
	}

	@Override
	public HandlerRegistration addDragEnterHandler(DragEnterHandler handler) {
		return textBox().addDragEnterHandler(handler);
	}

	@Override
	public HandlerRegistration addDragLeaveHandler(DragLeaveHandler handler) {
		return textBox().addDragLeaveHandler(handler);
	}

	@Override
	public HandlerRegistration addDragHandler(DragHandler handler) {
		return textBox().addDragHandler(handler);
	}

	@Override
	public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
		return textBox().addDragOverHandler(handler);
	}

	@Override
	public HandlerRegistration addDragStartHandler(DragStartHandler handler) {
		return textBox().addDragStartHandler(handler);
	}

	@Override
	public HandlerRegistration addDropHandler(DropHandler handler) {
		return textBox().addDropHandler(handler);
	}

	@Override
	public HandlerRegistration addFocusHandler(FocusHandler handler) {
		return textBox().addFocusHandler(handler);
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return textBox().addBlurHandler(handler);
	}

	@Override
	public HandlerRegistration addGestureStartHandler(GestureStartHandler handler) {
		return textBox().addGestureStartHandler(handler);
	}

	@Override
	public HandlerRegistration addGestureChangeHandler(GestureChangeHandler handler) {
		return textBox().addGestureChangeHandler(handler);
	}

	@Override
	public HandlerRegistration addGestureEndHandler(GestureEndHandler handler) {
		return textBox().addGestureEndHandler(handler);
	}

	@Override
	public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return textBox().addKeyDownHandler(handler);
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return textBox().addKeyUpHandler(handler);
	}

	@Override
	public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return textBox().addKeyPressHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return textBox().addMouseDownHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return textBox().addMouseUpHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return textBox().addMouseOutHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return textBox().addMouseOverHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return textBox().addMouseMoveHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return textBox().addMouseWheelHandler(handler);
	}

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		return textBox().addTouchStartHandler(handler);
	}

	@Override
	public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
		return textBox().addTouchMoveHandler(handler);
	}

	@Override
	public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
		return textBox().addTouchEndHandler(handler);
	}

	@Override
	public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
		return textBox().addTouchCancelHandler(handler);
	}

	@Override
	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		return textBox().addDoubleClickHandler(handler);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return textBox().addClickHandler(handler);
	}
}
