package gwt.material.design.client.base;

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
import gwt.material.design.client.ui.MaterialIcon;

/**
 * @author Ben Dol
 */
public abstract class AbstractIconAnchor extends AbstractAnchor implements HasIcon {

	private MaterialIcon icon = new MaterialIcon();

	public AbstractIconAnchor(String text, String href, IconType iconType) {
		this(iconType);
		setText(text);
		setHref(href);
	}

	public AbstractIconAnchor(String text, IconType iconType) {
		this(iconType);
		setText(text);
	}

	public AbstractIconAnchor(String text) {
		this();
		setText(text);
	}

	public AbstractIconAnchor(IconType iconType) {
		this();
		setIconType(iconType);
	}

	public AbstractIconAnchor() {
		super();
	}

	@Override
	public MaterialIcon getIcon() {
		return icon;
	}

	@Override
	public void setIconType(IconType iconType) {
		icon.setIconType(iconType);

		if(!icon.isAttached()) {
			add(icon);
		}
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
}