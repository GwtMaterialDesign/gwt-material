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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class MaterialTitle extends Composite {

	private static MaterialTitleUiBinder uiBinder = GWT
			.create(MaterialTitleUiBinder.class);

	interface MaterialTitleUiBinder extends UiBinder<Widget, MaterialTitle> {
	}

	private String title="";
	private String description = "";
	private String color="";
	private MaterialTitle materialTitle;
	private double fontSize;
	
	@UiField HTMLPanel titlePanel;
	@UiField Label lblTitle, lblDescription;
	
	public MaterialTitle(String title, String description){
		initWidget(uiBinder.createAndBindUi(this));
		setTitle(title);
		setDescription(description);
	}
	
	public MaterialTitle(String title){
		initWidget(uiBinder.createAndBindUi(this));
		setTitle(title);
	}
	
	public MaterialTitle() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		lblDescription.setText(description);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		lblTitle.setText(title);
	}
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		lblTitle.getElement().getStyle().setColor(color);
		lblDescription.getElement().getStyle().setColor(color);
	}

	public MaterialTitle getMaterialTitle() {
		return materialTitle;
	}

	public void setMaterialTitle(MaterialTitle materialTitle) {
		this.materialTitle = materialTitle;
	}



	public double getFontSize() {
		return fontSize;
	}



	public void setFontSize(double fontSize) {
		this.fontSize = fontSize;
		lblTitle.getElement().getStyle().setFontSize(fontSize, Unit.EM);
	}
	
	

}
