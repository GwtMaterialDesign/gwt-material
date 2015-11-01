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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialNoResult extends Composite {

	private static MaterialNoResultUiBinder uiBinder = GWT
		.create(MaterialNoResultUiBinder.class);

	interface MaterialNoResultUiBinder extends UiBinder<Widget, MaterialNoResult> {
	}

	@UiField MaterialIcon iconElem;
	@UiField MaterialTitle titleElem;
	@UiField MaterialPanel panel;
	
	private String color = "";
	private String textColor = "";
	private String icon = "";
	private String title = "";
	private String description = "";
	
	public MaterialNoResult() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public MaterialNoResult(String color, String textColor, String icon,
			String title, String description) {
		initWidget(uiBinder.createAndBindUi(this));
		setColor(color);
		setTextColor(textColor);
		setIcon(icon);
		setTitle(title);
		setDescription(description);
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		panel.setBackgroundColor(color);
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		iconElem.setTextColor(textColor);
		titleElem.setColor(textColor);
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		iconElem.setIcon(icon);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		titleElem.setTitle(title);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		titleElem.setDescription(description);
	}
}
