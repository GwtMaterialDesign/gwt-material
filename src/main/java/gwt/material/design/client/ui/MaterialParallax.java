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
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class MaterialParallax extends Composite {

	private static MaterialParallaxUiBinder uiBinder = GWT.create(MaterialParallaxUiBinder.class);

	interface MaterialParallaxUiBinder extends UiBinder<Widget, MaterialParallax> {
	}

	@UiField MaterialRow container;
	@UiField MaterialPanel imageContainer;
	@UiField Image image;

	private String height;
	private String url;
	private ImageResource resource;

	public MaterialParallax() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public MaterialParallax(String url) {
		super();
		this.url = url;
	}

	public MaterialParallax(ImageResource resource) {
		super();
		this.resource = resource;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		initParallax();
	}

	@UiChild(tagname = "content")
	public void onAddContent(Widget w) {
		container.add(w);
	}

	public native void initParallax() /*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('.parallax').parallax();
		});
	}-*/;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		image.setUrl(url);
	}

	public ImageResource getResource() {
		return resource;
	}

	public void setResource(ImageResource resource) {
		this.resource = resource;
		image.setResource(resource);
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
		imageContainer.getElement().getStyle().setHeight(Double.parseDouble(height), Unit.PX);
	}
}
