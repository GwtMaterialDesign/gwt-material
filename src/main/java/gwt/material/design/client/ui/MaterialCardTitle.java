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

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.CustomSpan;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasIcons;

import com.google.gwt.dom.client.Document;

//@formatter:off
/**
* Card Element for card title. 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#cards">Material Cards</a>
*/
//@formatter:on
public class MaterialCardTitle extends ComplexWidget implements HasIcons, HasColors {

	private MaterialIcon iconElem = new MaterialIcon();
	private CustomSpan spanElem = new CustomSpan();
	private String text;
	
	public MaterialCardTitle() {
		setElement(Document.get().createSpanElement());
		setStyleName("card-title activator");
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

	@Override
	public void setSize(String size) {
		iconElem.addStyleName(size);
	}

	@Override
	public void setIconColor(String iconColor) {
		iconElem.addStyleName(iconColor + "-text");
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		spanElem.setText(text);
		add(spanElem);
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
