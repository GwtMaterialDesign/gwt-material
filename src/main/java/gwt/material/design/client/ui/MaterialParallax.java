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
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.custom.MaterialWidget;

//@formatter:off
/**
* Mateiral Parallax is an effect where the background content or image in this case, is moved at a different speed than the foreground content while scrolling. Check out the demo to get a better idea of it.
*
* <h3>UiBinder Usage:</h3>
* <pre>
* {@code

<m:MaterialParallax url="http://mayastepien.nl/googlecalendar/google-drinks.jpg" height="400">
	<m:content>
		<m:MaterialTitle title="Parallax" description="Parallax is an effect where the background content orimage in this case, is moved at a different speed than the foreground content while scrolling."/>
	</m:content>
</m:MaterialParallax>

* }
*<pre>
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#showcase">Material Parallax</a>
*/
//@formatter:on
public class MaterialParallax extends MaterialWidget {

	private static MaterialParallaxUiBinder uiBinder = GWT.create(MaterialParallaxUiBinder.class);

	interface MaterialParallaxUiBinder extends UiBinder<Widget, MaterialParallax> {
	}

	@UiField MaterialRow container;
	@UiField MaterialPanel imageContainer;
	@UiField Image image;

	private ImageResource resource;

	public MaterialParallax() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public MaterialParallax(String url) {
		super();
		setUrl(url);
	}

	public MaterialParallax(ImageResource resource) {
		super();
		setResource(resource);
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
		return image.getUrl();
	}

	public void setUrl(String url) {
		image.setUrl(url);
	}

	public ImageResource getResource() {
		return resource;
	}

	public void setResource(ImageResource resource) {
		this.resource = resource;
		image.setResource(resource);
	}

	public double getHeight() {
		return Double.parseDouble(
			imageContainer.getElement().getStyle().getHeight());
	}

	public void setHeight(double height) {
		imageContainer.getElement().getStyle().setHeight(height, Unit.PX);
	}
}
