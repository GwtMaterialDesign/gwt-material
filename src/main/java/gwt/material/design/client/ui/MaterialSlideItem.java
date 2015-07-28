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
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSlideItem extends Composite {

	private static MaterialSlideItemUiBinder uiBinder = GWT.create(MaterialSlideItemUiBinder.class);

	interface MaterialSlideItemUiBinder extends UiBinder<Widget, MaterialSlideItem> {
	}

	@UiField MaterialLabel lblTitle, lblDescription;
	@UiField MaterialPanel caption;
	@UiField Image imgSlide;
	
	private String title, description;
	private String textColor;
	private String align;
	
	private String url;
	private ImageResource resource;
	
	public MaterialSlideItem() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getTitle() {
		return title;
	}
	
	/**
	 * Set the title caption of the slide
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
		lblTitle.setText(title);
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Set the description caption of the slide
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
		lblDescription.setText(description);
	}

	public String getTextColor() {
		return textColor;
	}

	/**
	 * Set the text color of the title and description captions
	 * @param textColor
	 */
	public void setTextColor(String textColor) {
		this.textColor = textColor;
		lblDescription.setTextColor(textColor);
		lblTitle.setText(textColor);
	}

	public String getAlign() {
		return align;
	}

	/**
	 * Set the caption text alignment
	 * @param align
	 */
	public void setAlign(String align) {
		this.align = align;
		caption.addStyleName(align + "-align");
	}

	public String getUrl() {
		return url;
	}

	/**
	 * Set the image url of the backgroud slide
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
		imgSlide.setUrl(url);
	}

	public ImageResource getResource() {
		return resource;
	}

	/**
	 * You can define also the image resource if you are not using image URL
	 * @param resouce
	 */
	public void setResouce(ImageResource resource) {
		this.resource = resource;
		imgSlide.setResource(resource);
	}

	
	
}
