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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;

import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.WavesType;
import gwt.material.design.client.base.AbstractButton;
import gwt.material.design.client.base.HasIcon;
import gwt.material.design.client.base.HasSeparator;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.base.helper.StyleHelper;

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
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://www.google.com/design/icons/">Search Google Icons</a>
 * @see <a href="http://gwt-material-demo.herokuapp.com/#icons">Material Icons Documentation</a>
 */
//@formatter:on
public class MaterialIcon extends AbstractButton implements HasSeparator, HasIcon {

	private IconSize size;
	private IconPosition position;

	/**
	 * Creates an empty icon.
	 */
	public MaterialIcon() {
		super();
		addStyleName("material-icons");
	}
	
	/**
	 * Sets a simple icon with black textcolor.
	 */
	public MaterialIcon(IconType icon) {
		this();
		setIconType(icon);
	}
	
	/**
	 * Sets an icon with textColor and backgroundColor.
	 */
	public MaterialIcon(IconType icon, String textColor, String bgColor) {
		this();
		setIconType(icon);
		setTextColor(textColor);
		setBackgroundColor(bgColor);
	}

	@Override
	protected Element createElement() {
		return Document.get().createElement("i");
	}

	@Override
	public MaterialIcon getIcon() {
		return this;
	}

	@Override
	public void setIconType(IconType icon) {
		getElement().setInnerText(icon.getCssName());
	}

	@Override
	public void setIconPosition(IconPosition position) {
		if(this.position != null) {
			removeStyleName(this.position.getCssName());
		}
		this.position = position;

		if(position != null) {
			addStyleName(position.getCssName());
		}
	}

	@Override
	public void setIconSize(IconSize size) {
		if(this.size != null) {
			removeStyleName(this.size.getCssName());
		}
		this.size = size;

		if(size != null) {
			addStyleName(size.getCssName());
		}
	}

	@Override
	public void setIconColor(String iconColor) {
		getElement().getStyle().setColor(iconColor);
	}

	@Override
	public void setIconFontSize(double size, Style.Unit unit) {
		getElement().getStyle().setFontSize(size, unit);
	}

	@Override
	public void setIconPrefix(boolean prefix) {
		removeStyleName("prefix");

		if(prefix) {
			addStyleName("prefix");
		}
	}

	@Override
	public boolean isIconPrefix() {
		return StyleHelper.containsStyle(getStyleName(), "prefix");
	}

	@Override
	public void setWaves(WavesType waves) {
		super.setWaves(waves);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
	}
}
