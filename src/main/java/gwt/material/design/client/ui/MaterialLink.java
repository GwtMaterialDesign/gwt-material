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

import com.google.gwt.dom.client.Element;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.custom.AbstractButton;
import gwt.material.design.client.custom.AbstractIconButton;
import gwt.material.design.client.custom.CustomSpan;
import gwt.material.design.client.custom.HasIcon;
import gwt.material.design.client.custom.HasSeparator;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
* 
* <p>Using Material Link you can easily add href functionality into your app for navigation
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* Links
* <m:MaterialLink2 href="#normal" text="Normal Link" textColor="red" icon="polymer" iconPosition="left" grid=" "/>
* 
* <m:MaterialLink2 href="#material" text="Link with Href" textColor="red" icon="polymer" iconPosition="left" grid=" "/>
* 
* <m:MaterialLink2 href="#design" text="Link with Different Icon color" textColor="black" icon="polymer" iconPosition="left" iconColor="red" grid=" "/>}
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#buttons">Material Link</a>
*/
//@formatter:on
public class MaterialLink extends AbstractIconButton {

	public MaterialLink(String text) {
		super();
		setText(text);
		setHref("#");
	}

	/**
	 * Normal link with href.
	 */
	public MaterialLink(String text, String href) {
		super();
		setText(text);
		setHref(href);
	}

	/**
	 * Creates a link with icon.
	 */
	public MaterialLink(String text, String href, IconType icon) {
		super();
		setText(text);
		setHref(href);
		setIconType(icon);
	}

	@Override
	protected Element createElement() {
		Element element = Document.get().createElement("a");
		element.getStyle().setDisplay(Display.INLINE_BLOCK);
		element.getStyle().setCursor(Cursor.POINTER);
		return element;
	}
}
