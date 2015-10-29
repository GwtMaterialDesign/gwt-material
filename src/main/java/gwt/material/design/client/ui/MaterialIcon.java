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
import gwt.material.design.client.custom.HasIcons;
import gwt.material.design.client.custom.HasSeparator;
import gwt.material.design.client.type.IconType;

import com.google.gwt.dom.client.Document;

//@formatter:off
/**
* We have included 740 Material Design Icons courtesy of Google. You can download them directly from the Material Design specs.
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* <m:MaterialIcon waves="light" icon="polymer"/>
* <m:MaterialIcon waves="light" icon="polymer" textColor="blue" type="CIRCLE"/>
* <m:MaterialIcon waves="light" icon="polymer" backgroundColor="blue" textColor="white" type="CIRCLE" tooltip="Tooltip" tooltipLocation="bottom"/>
* }
* 
* </pre>
* 
* @author kevzlou7979
* @see <a href="http://www.google.com/design/icons/">Search Google Icons</a>
* @see <a href="http://gwt-material-demo.herokuapp.com/#icons">Material Icons Documentation</a>
*/
//@formatter:on
public class MaterialIcon extends ButtonBase implements HasSeparator, HasIcons{

	/**
	 * Creates an empty icon
	 */
	public MaterialIcon() {
		setElement(Document.get().createElement("i"));
		addStyleName("material-icons");
	}
	
	/**
	 * Sets a simple icon with black textcolor
	 * @param icon
	 */
	public MaterialIcon(String icon){
		this();
		setIcon(icon);
	}
	
	/**
	 * Sets an icon with textColor and backgroundColor
	 * @param icon
	 * @param textColor
	 * @param bgColor
	 */
	public MaterialIcon(String icon, String textColor, String bgColor){
		this();
		setIcon(icon);
		setTextColor(textColor);
		setBackgroundColor(bgColor);
	}
	
	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		initWaves();
	}

	@Override
	protected void onUnload() {
		// TODO Auto-generated method stub
		super.onUnload();
	}

	@Override
	public void setSeparator(boolean separator) {
		if (separator) {
			this.getElement().setAttribute("style", "border-bottom: 1px solid #e9e9e9;");
		}
	}


	@Override
	public void setIcon(String icon) {
		getElement().setInnerHTML(icon);
	}


	@Override
	public void setIconPosition(String iconPosition) {
		addStyleName(iconPosition);
	}


	@Override
	public void setSize(String size) {
		addStyleName(size);
	}
	
	@Override
	public void setWaves(String waves) {
		addStyleName("waves-effect waves-circle waves-" + waves);
		getElement().getStyle().setProperty("width", "initial");
		getElement().getStyle().setProperty("height", "auto");
		getElement().getStyle().setProperty("textAlign", "center");
		getElement().getStyle().setProperty("padding", "0.2em");
	}

	public void setType(IconType type) {
		switch (type) {
		case CIRCLE:
			addStyleName("circle");
			break;

		default:
			break;
		}
	}

	@Override
	public void setIconColor(String iconColor) {
		addStyleName(iconColor + "-text");
	}
	
	public void setIconBackgroundColor(String bgColor){
		addStyleName(bgColor);
	}

}
