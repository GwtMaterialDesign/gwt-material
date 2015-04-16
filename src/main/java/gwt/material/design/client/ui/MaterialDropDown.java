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

import gwt.material.design.client.custom.MaterialWidget;
import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class MaterialDropDown extends MaterialWidget {

	private static MaterialDropDownUiBinder uiBinder = GWT.create(MaterialDropDownUiBinder.class);

	interface MaterialDropDownUiBinder extends UiBinder<Widget, MaterialDropDown> {
	}

	private String text = "";
	private String activates = "";
	private boolean divider;

	@UiField
	MaterialButton anchor;
	@UiField
	UnorderedList list;

	public MaterialDropDown() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		activates = String.valueOf(hashCode());
		if(!activates.isEmpty()){
			anchor.getElement().setAttribute("data-activates", activates);
			list.getElement().setId(activates);
		}
		
		super.setWidget(anchor.getButton());
		super.applyMaterialEffect();
		initDropDown();
		
	}

	public native void initDropDown()/*-{
		$wnd.jQuery('.dropdown-button').dropdown({
			inDuration : 300,
			outDuration : 225,
			constrain_width : true, // Does not change width of dropdown to that of the activator
			hover : false, // Activate on click
			alignment : 'top', // Aligns dropdown to left or right edge (works with constrain_width)
			gutter : 0, // Spacing from edge
			belowOrigin : false
		// Displays dropdown below the button
		});
	}-*/;

	@UiChild(tagname = "item")
	public void addWidget(final Widget item) {
		list.add(new ListItem(item));
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		if (!text.isEmpty())
			anchor.setText(text);
		list.addStyleName(MaterialResources.INSTANCE.materialcss().collection());
	}

	public boolean isDivider() {
		return divider;
	}

	public void setDivider(boolean divider) {
		this.divider = divider;
		if(isDivider()){
			for(Widget w : list){
				if(w instanceof ListItem){
					w.getElement().setAttribute("style", "border-bottom: 1px solid #e9e9e9;");
				}
			}
		}
		
	}

	public String getActivates() {
		return activates;
	}

	public void setActivates(String activates) {
		this.activates = activates;
	}

}
