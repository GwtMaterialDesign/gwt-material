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

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCollapsibleItem extends ListItem{

	public MaterialCollapsibleItem() {
		// TODO Auto-generated constructor stub
	}
	
	private UnorderedList ulPanel;
	private Widget header;
	
	@UiChild(tagname = "header")
	public void addHeader(final Widget header) {
		header.addStyleName("collapsible-header");
		setHeader(header);
		this.add(header);
	}
	
	@UiChild(tagname = "content")
	public void addContent(final Widget content) {
		ulPanel = new UnorderedList();
		ulPanel.addStyleName("collapsible-body");
		
		ulPanel.add(content);
		ulPanel.getElement().getStyle().setPadding(2, Unit.EM);
		ulPanel.getElement().getStyle().setMargin(0, Unit.EM);
		this.add(ulPanel);
	}

	public Widget getHeader() {
		return header;
	}

	public void setHeader(Widget header) {
		this.header = header;
	}
	
}
