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

import gwt.material.design.client.custom.CustomSpan;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasType;
import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.user.client.ui.HasText;

//@formatter:off
/**
* Badges can notify you that there are new or unread messages or notifications. Add the new class to the badge to give it the background.
* 
* 
* <p>
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* <m:MaterialBadge text="1 new" color="blue"/>
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#badges">Material Badge</a>
*/
//@formatter:on
public class MaterialBadge  extends CustomSpan implements HasColors, HasText, HasType{
	
	/**
	 * Creates  a badge component that can be added to Link , Collection , DropDown, Sidenav and any other Material components
	 */
	public MaterialBadge() {
		setStyleName("badge");
		addStyleName(MaterialResources.INSTANCE.materialcss().badge());
	}
	
	
	/**
	 * Badge with text and color
	 * @param text
	 * @param color
	 */
	
	
	
	public MaterialBadge(String text, String textColor, String bgColor) {
		this();
		setText(text);
		setTextColor(textColor);
		setBackgroundColor(bgColor);
	}

	@Override
	public void setType(String type) {
		addStyleName(type);
	}


	@Override
	public String getText() {
		return getElement().getInnerHTML();
	}


	@Override
	public void setText(String text) {
		getElement().setInnerHTML(text);
	}


	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}


	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text");
	}
	


}