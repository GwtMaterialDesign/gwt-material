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
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.custom.HasIcon;
import gwt.material.design.client.custom.MaterialWidget;

public class MaterialNoResult extends MaterialWidget implements HasIcon {

	private static MaterialNoResultUiBinder uiBinder = GWT
		.create(MaterialNoResultUiBinder.class);

	interface MaterialNoResultUiBinder extends UiBinder<Widget, MaterialNoResult> {
	}

	@UiField MaterialPanel panel;
	@UiField MaterialIcon icon;
	@UiField MaterialTitle title;

	public MaterialNoResult() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public MaterialNoResult(String bgColor, String textColor, IconType iconType,
			String title, String description) {
		this();

		setBackgroundColor(bgColor);
		setTextColor(textColor);
		setIconType(iconType);
		setTitle(title);
		setDescription(description);
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		super.setBackgroundColor(bgColor);
		panel.setBackgroundColor(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		super.setTextColor(textColor);
		icon.setTextColor(textColor);
		title.setTextColor(textColor);
	}

	@Override
	public MaterialIcon getIcon() {
		return icon;
	}

	@Override
	public void setIconType(IconType iconType) {
		icon.setIconType(iconType);
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

	public String getTitle() {
		return title.getTitle();
	}

	public void setTitle(String title) {
		this.title.setTitle(title);
	}

	public String getDescription() {
		return title.getDescription();
	}

	public void setDescription(String description) {
		title.setDescription(description);
	}
}
