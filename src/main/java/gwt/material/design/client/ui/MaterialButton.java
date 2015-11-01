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

import gwt.material.design.client.custom.ButtonBase;
import gwt.material.design.client.custom.CustomSpan;
import gwt.material.design.client.custom.HasIcons;
import gwt.material.design.client.custom.Waves;
import gwt.material.design.client.type.ButtonType;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

//@formatter:off
/**
 * There are 3 main button types described in material design. The raised
 * button is a standard button that signify actions and seek to give depth
 * to a mostly flat page. The floating circular action button is meant for
 * very important functions. Flat buttons are usually used within elements
 * that already have depth like cards or modals.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code// Raised (Default) Button
 * <m:MaterialButton text="Button" waves="light" backgroundColor="blue" />
 *
 * // Adding icon
 * <m:MaterialButton text="Button" waves="light" backgroundColor="blue" icon="cloud" iconPosition="left"/>
 *
 * // Floating Button
 * <m:MaterialButton type="floating" waves="light" size="large"  icon="add"/>
 *
 * // Flat Button
 * <m:MaterialButton text="Button" type="flat" waves="grey" />
 *
 * // Large Button
 * <m:MaterialButton size="large" text="Button" waves="light" backgroundColor="blue" icon="cloud" iconPosition="right"/>}
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#buttons">Material Button</a>
 */
//@formatter:on
public class MaterialButton extends ButtonBase implements HasText, HasIcons{

	private MaterialIcon iconElem = new MaterialIcon();
	private CustomSpan spanElem = new CustomSpan();
	private String text;
	
	/**
	 * Creates an empty button
	 */
	public MaterialButton() {
		setElement(Document.get().createElement("button"));
		setStyleName("btn");
	}
	
	/**
	 * Raised buttons
	 * @param text - The text of the button
	 * @param bgColor - The background color of the button. See the color palette here http://gwt-material.appspot.com/#colors
	 * @param waves - A material design aspect that helps user to add ripple effect on button
	 */
	public MaterialButton(String text, String bgColor, String waves) {
		this();
		setText(text);
		setBackgroundColor(bgColor);
		setWaves(waves);
	}
	
	/**
	 * Material Floating button
	 * @param icon - Icon to be applied. See reference here http://gwt-material.appspot.com/#icons
	 * @param bgColor - The background color of the button. See the color palette here http://gwt-material.appspot.com/#colors
	 * @param type - Set it to floating
	 * @param waves - A material design aspect that helps user to add ripple effect on button
	 * @param tooltip - The tooltip to be shown when it is hovered
	 */
	public MaterialButton(String icon,
						  String bgColor,
						  ButtonType type,
						  String waves,
						  String tooltip,
						  String tooltipLocation) {
		this();
		setType(type);
		setBackgroundColor(bgColor);
		setIcon(icon);
		setType(type);
		setWaves(waves);
		setTooltip(tooltip);
		setTooltipLocation(tooltipLocation);
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
	public MaterialButton(String text,
						  ButtonType type,
						  String icon,
						  String iconPosition,
						  String size,
						  String tooltip,
						  String tooltipLocation) {
		this();
		setText(text);
		setType(type);
		setIcon(icon);
		setIconPosition(iconPosition);
		setSize(size);
		setTooltip(tooltip);
		setTooltipLocation(tooltipLocation);
	}

	@Override
	public void setIcon(String icon) {
		iconElem.setIcon(icon);
		add(iconElem);
	}

	@Override
	public void setIconPosition(String iconPosition) {
		iconElem.setIconPosition(iconPosition);
	}

	/**
	 * Button size
	 * - Large
	 * - Medium (Default)
	 */
	public void setSize(String size) {
		addStyleName("btn-" + size);
	}

	/**
	 * ButtonType to be applied into your component
	 * - RAISED (Default)
	 * - FLAT
	 * - FLOATING
	 */
	public void setType(ButtonType type) {
		switch (type) {
		case FLAT:
			addStyleName("btn-flat");
			removeStyleName("btn");
			break;
		case FLOATING:
			addStyleName("btn-floating");
			break;
		default:
			// default : raised
			break;
		}
	}

	@Override
	public void setText(String text) {
		this.text = text;
		spanElem.setText(text);
		add(spanElem);
	}
	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setIconColor(String iconColor) {
		iconElem.addStyleName(iconColor + "-text");
	}
}
