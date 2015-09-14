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

import gwt.material.design.client.custom.CustomIcon;
import gwt.material.design.client.custom.CustomInput;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasKeyUpHandlers;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSearch extends Composite implements HasText,HasKeyUpHandlers {

	private static MaterialSearchUiBinder uiBinder = GWT
			.create(MaterialSearchUiBinder.class);

	interface MaterialSearchUiBinder extends UiBinder<Widget, MaterialSearch> {
	}
	
	@UiField CustomInput txtSearch;
	@UiField CustomIcon iconElem;
	@UiField MaterialPanel panel;
	
	private String color="";
	private String text="";
	private String textColor="";
	private String placeholder  = "";
	private String id = "";

	public MaterialSearch() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Composite#onAttach()
	 */
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		id = DOM.createUniqueId();
		txtSearch.getElement().setId(id);
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return addDomHandler(handler, KeyUpEvent.getType());
	}

	@Override
	public String getText() {
		return getInputTextValue(id);
	}

	@Override
	public void setText(String text) {
		  this.text = text;
		  setInputTextValue(text, id);
	}
	
	public native String getInputTextValue(String id)/*-{
		return $wnd.document.getElementById(id).value;
	}-*/;
	
	public native void setInputTextValue(String value, String id)/*-{
		$wnd.document.getElementById(id).value = value;
	}-*/;
	
	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		txtSearch.addStyleName(textColor + "-text");
		iconElem.addStyleName(textColor + "-text");
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		txtSearch.setPlaceholder(placeholder);
	}

}


