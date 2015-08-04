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

import com.google.gwt.core.client.GWT;
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
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
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
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasEnabled;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTextBox extends Composite implements
		HasChangeHandlers, HasName, HasDirectionEstimator, HasValue<String>,
		HasText, AutoDirectionHandler.Target, IsEditor<ValueBoxEditor<String>>,
		HasKeyUpHandlers, HasClickHandlers, HasDoubleClickHandlers,
		HasEnabled, HasAllDragAndDropHandlers, HasAllFocusHandlers,
		HasAllGestureHandlers, HasAllKeyHandlers, HasAllMouseHandlers,
		HasAllTouchHandlers {

	private static MaterialTextBoxUiBinder uiBinder = GWT.create(MaterialTextBoxUiBinder.class);

	interface MaterialTextBoxUiBinder extends UiBinder<Widget, MaterialTextBox> {
	}

	private String placeholder;
	private String type = "text";
	private String icon = "";
	private boolean isValid = true;
	private boolean enabled;
	private String length;

	@UiField
	protected CustomLabel customLabel;
	@UiField
	protected Label lblName;
	@UiField
	protected TextBox txtBox;
	@UiField
	protected CustomIcon iconPanel;

	public MaterialTextBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		String name = String.valueOf(hashCode());
		txtBox.getElement().setId(name);
		customLabel.getElement().setAttribute("for", name);
	}

	public void setInvalid() {
		backToDefault();
		txtBox.getElement().addClassName("invalid");
		isValid = false;
	}

	public void setValid() {
		backToDefault();
		txtBox.getElement().addClassName("valid");
		isValid = true;
	}

	/**
	 * Resets the textbox by removing its content and resetting visual state.
	 */
	public void clear() {
		txtBox.setText("");
		backToDefault();
		customLabel.removeStyleName("active");
	}

	public void backToDefault() {
		txtBox.getElement().removeClassName("valid");
		txtBox.getElement().removeClassName("invalid");
	}

	@Override
	public String getText() {
		return txtBox.getText();
	}

	@Override
	public void setText(String text) {
		txtBox.setText(text);

		if (!text.isEmpty()) {
			customLabel.addStyleName("active");
		}
	}

	public String getPlaceholder() {
		return placeholder;

	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		lblName.setText(placeholder);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		txtBox.getElement().setAttribute("type", type);
		if(type.equals("number")){
			txtBox.addKeyPressHandler(new KeyPressHandler() {

				@Override
				public void onKeyPress(KeyPressEvent event) {
					 if (!Character.isDigit(event.getCharCode())
			                    && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_TAB
			                    && event.getNativeEvent().getKeyCode() != KeyCodes.KEY_BACKSPACE
			                    && event.getNativeEvent().getKeyCode() != 190){
			                ((TextBox) event.getSource()).cancelKey();
			         }
				}
			});
		}
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		iconPanel.addStyleName(icon + " prefix");
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		txtBox.setEnabled(enabled);
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return addDomHandler(handler, KeyUpEvent.getType());
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
		txtBox.getElement().setAttribute("length", length);
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
			customLabel.addStyleName("active");
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
}
