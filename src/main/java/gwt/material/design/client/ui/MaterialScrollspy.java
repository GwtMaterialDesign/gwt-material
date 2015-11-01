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
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialScrollspy extends Composite {

	private static MaterialScrollspyUiBinder uiBinder = GWT.create(MaterialScrollspyUiBinder.class);

	interface MaterialScrollspyUiBinder extends UiBinder<Widget, MaterialScrollspy> {
	}
	
	@UiField UnorderedList ulPanel;

	private String color = "";
	
	public MaterialScrollspy() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiChild(tagname = "item")
	public void onAddItem(Widget w) {
		ListItem item = new ListItem(w);
		ulPanel.add(item);
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		initScrollspy();
	}

	static native void initScrollspy()/*-{
		$wnd.jQuery(".scrollspy").scrollSpy();
	}-*/;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
