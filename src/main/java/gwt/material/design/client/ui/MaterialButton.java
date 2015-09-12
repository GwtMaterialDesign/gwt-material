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

import gwt.material.design.client.custom.CustomAnchor;
import gwt.material.design.client.custom.CustomButton;
import gwt.material.design.client.custom.CustomIcon;
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.MaterialWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialButton extends MaterialWidget implements HasClickHandlers,HasDoubleClickHandlers, HasText, HasGrid{

	private static MaterialButtonUiBinder uiBinder = GWT.create(MaterialButtonUiBinder.class);

	interface MaterialButtonUiBinder extends UiBinder<Widget, MaterialButton> {
	}
	
	@UiField HTMLPanel panel;
	@UiField CustomButton button;
	@UiField CustomAnchor anchor;
	@UiField CustomIcon iconElem;
	@UiField Label label;
	
	private String text = "";
	private String type = "";
	private String icon = "";
	private String iconPosition = "";
	private String size = "";
	private String width = "";
	private String href= "";
	private String textColor="";

	/**
	 * MaterialButtons provide an easy way to add icons , custom colors , waves effects and other functionalities.
	 */
	public MaterialButton() {
		initWidget(uiBinder.createAndBindUi(this));
		initButtonStyles();
	}
	
	/**
	 * Raised buttons
	 * @param text - The text of the button
	 * @param color - The background color of the button. See the color palette here http://gwt-material.appspot.com/#colors
	 * @param waves - A material design aspect that helps user to add ripple effect on button
	 */
	public MaterialButton(String text, String color, String waves) {
		initWidget(uiBinder.createAndBindUi(this));
		this.text = text;
		this.color = color;
		this.waves = waves;
		initButtonStyles();
	}
	
	/**
	 * Material Floating button
	 * @param icon - Icon to be applied. See reference here http://gwt-material.appspot.com/#icons
	 * @param color - The background color of the button. See the color palette here http://gwt-material.appspot.com/#colors
	 * @param type - Set it to floating
	 * @param waves - A material design aspect that helps user to add ripple effect on button
	 * @param tooltip - The tooltip to be shown when it is hovered
	 */
	public MaterialButton(String icon, String color, String type, String waves, String tooltip) {
		initWidget(uiBinder.createAndBindUi(this));
		this.icon = icon;
		this.color = color;
		this.type = type;
		this.waves = waves;
		this.tooltip = tooltip;
		initButtonStyles();
	}

	/**
	 * Material Flat Button
	 * @param text - The text of the button
	 * @param type - Set it to flat
	 * @param icon - Icon to be applied. See reference here http://gwt-material.appspot.com/#icons
	 * @param iconPosition - Icon position can be left or right
	 * @param size - small, medium or large
	 * @param tooltip - The tooltip to be shown when it is hovered
	 */
	public MaterialButton(String text, String type, String icon, String iconPosition, String size, String tooltip) {
		initWidget(uiBinder.createAndBindUi(this));
		this.text = text;
		this.type = type;
		this.icon = icon;
		this.iconPosition = iconPosition;
		this.size = size;
		this.tooltip = tooltip;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		initButtonStyles();
	}

	/**
	 * Init the styles based on types
	 */
	private void initButtonStyles(){
		switch (type) {
		case "flat":
			changeType(anchor);
			break;
		default:
			changeType(button);
			button.addStyleName("btn");
			break;
		}
		super.applyMaterialEffect();
	}
	
	/**
	 * Building the structure markup of button based on types
	 * @param w - panel
	 */
	private void changeType(ComplexPanel w){
		super.setWidget(w);
	
		panel.clear();
		w.add(iconElem);
		w.removeFromParent();
		
		if(isDisable()){
			w.getElement().setAttribute("disabled", "true");
		}
		
		if(!type.isEmpty()) w.addStyleName("btn-" + type);
		
		if(!textColor.isEmpty()){ 
			label.addStyleName(textColor + "-text");
			iconElem.addStyleName(textColor + "-text");
		}
		
		if(!text.isEmpty()){
			label.setText(text);
		}else {
			label.setVisible(false);
		}
		w.add(label);
			
		
		if(!icon.isEmpty()) iconElem.addStyleName(icon);
		else iconElem.removeFromParent();
		
		
		if(!iconPosition.isEmpty()) iconElem.addStyleName(iconPosition);
		
		if(!size.isEmpty()) button.addStyleName("btn-" + size);
		
		if(!width.isEmpty()) {
			this.getElement().setAttribute("style", "width: " + width+ ";");
			w.getElement().setAttribute("style", "width: " + width+ ";");
		}
		
		
		panel.add(w);
	}

	public String getText() {
		return text;
	}
	
	/**
	 * Set the text of the button
	 */
	public void setText(String text) {
		this.text = text;
		label.setText(text);
		label.setVisible(true);
	}

	public String getType() {
		return type;
	}

	
	/**
	 * Set the type of the button
	 * 1. default - raised button 
	 * 2. flat - used on modals
	 * 3. floating - used as a circular button especially on MaterialFloating container
	 */
	public void setType(String type) {
		this.type = type;
		initButtonStyles();
	}

	public String getIcon() {
		return icon;
	}

	/**
	 * Set the icon of the button see reference @ http://gwt-material.appspot.com/#icons
	 * @param icon
	 */
	public void setIcon(String icon) {
	    if (iconElem != null && !this.icon.isEmpty()) iconElem.removeStyleName(this.icon);
		this.icon = icon;
		initButtonStyles();
	}

	public String getIconPosition() {
		return iconPosition;
	}

	/**
	 * Set the icon position
	 * 1. left
	 * 2. right
	 * @param iconPosition
	 */
	public void setIconPosition(String iconPosition) {
		this.iconPosition = iconPosition;
		initButtonStyles();
	}

	public String getSize() {
		return size;
	}

	/**
	 * Set the size of the button :
	 * 1. small
	 * 2. medium
	 * 3. large
	 * @param size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	public String getWidth() {
		return width;
	}


	/**
	 * Set the width of the button
	 */
	public void setWidth(String width) {
		this.width = width;
	}


	public CustomButton getButton() {
		return button;
	}


	public void setButton(CustomButton button) {
		this.button = button;
		initButtonStyles();
	}

	public String getHref() {
		return href;
	}

	/**
	 * Set the href url of the button
	 * @param href
	 */
	public void setHref(String href) {
		this.href = href;
		this.getElement().setAttribute("href", href);
	}

	public String getTextColor() {
		return textColor;
	}

	/**
	 * Set the Text Color of the button
	 * @param textColor
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	@Override
	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		if(isDisable()){
			return null;
		}
		return addDomHandler(handler, DoubleClickEvent.getType());
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		if(isDisable()){
			return null;
		}
		return addDomHandler(handler, ClickEvent.getType());
	}

	@Override
	public void setGrid(String grid) {
		panel.addStyleName("col " + grid);
	}
	
}
