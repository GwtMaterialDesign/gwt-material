package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterialDesign
 * %%
 * Copyright (C) 2015 GwtMaterial
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

import gwt.material.design.client.custom.ListItem;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCollapsibleItem extends ListItem{

	public MaterialCollapsibleItem() {
		// TODO Auto-generated constructor stub
	}
	
	@UiChild(tagname = "header")
	public void addHeader(final Widget header) {
		header.addStyleName("collapsible-header");
		this.add(header);
	}
	
	@UiChild(tagname = "content")
	public void addContent(final Widget content) {
		MaterialPanel panel = new MaterialPanel("");
		panel.addStyleName("collapsible-body");
		panel.add(content);
		panel.getElement().getStyle().setPadding(2, Unit.EM);
		panel.getElement().getStyle().setMargin(0, Unit.EM);
		this.add(panel);
	}
	
}
