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

import com.google.gwt.dom.client.Style;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.custom.CustomInput;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.HasIcon;
import gwt.material.design.client.custom.HasPlaceholder;
import gwt.material.design.client.custom.MaterialWidget;

//@formatter:off
/**
* Material Search is a value box component that returs a result based on your search
* 
* <p>
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code 
* <m:MaterialSearch placeholder="Sample"/>
* }
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#grid">Material Column</a>
*/
//@formatter:on
public class MaterialSearch extends MaterialWidget implements HasText, HasKeyUpHandlers, HasIcon, HasPlaceholder {

	private static MaterialSearchUiBinder uiBinder = GWT
		.create(MaterialSearchUiBinder.class);

	interface MaterialSearchUiBinder extends UiBinder<Widget, MaterialSearch> {
	}
	
	@UiField CustomInput searchInput;
	@UiField MaterialIcon icon;
	@UiField MaterialPanel panel;

	public MaterialSearch() {
		initWidget(uiBinder.createAndBindUi(this));

		icon.setIconPrefix(true);
	}

	@Override
	protected void onAttach() {
		super.onAttach();

		searchInput.setId(DOM.createUniqueId());
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return addDomHandler(handler, KeyUpEvent.getType());
	}

	@Override
	public String getText() {
		return searchInput.getText();
	}

	@Override
	public void setText(String text) {
		searchInput.setText(text);
	}

	@Override
	public void setTextColor(String textColor) {
		super.setTextColor(textColor);

		searchInput.setTextColor(textColor);
		icon.setTextColor(textColor);
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

	@Override
	public String getPlaceholder() {
		return searchInput.getPlaceholder();
	}

	@Override
	public void setPlaceholder(String placeholder) {
		searchInput.setPlaceholder(placeholder);
	}
}


