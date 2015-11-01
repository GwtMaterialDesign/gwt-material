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

import gwt.material.design.client.custom.ButtonBase;
import gwt.material.design.client.custom.CustomSpan;
import gwt.material.design.client.custom.HasIcons;
import gwt.material.design.client.custom.HasImage;

import com.google.gwt.dom.client.Document;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

//@formatter:off
/**
 * Chips can be used to represent small blocks of information.
 * They are most commonly used either for contacts or for tags.
 *
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code// Simple Chips
 * <m:MaterialChip text="Default" icon="close"/>
 *
 * // Static Chip
 * <m:MaterialChip text="Apple" />
 *
 * // Contact Chips
 * <m:MaterialChip url="http://b.vimeocdn.com/ps/339/488/3394886_300.jpg" text="Yunalis Mat Zara'ai" icon="close"/>
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#chips">Material Chips</a>
 */
//@formatter:on
public class MaterialChip extends ButtonBase implements HasImage, HasIcons {

	private String text = "";
	private String url = "";
	private ImageResource resource;
	private Image image = new Image();
	private MaterialIcon icon = new MaterialIcon();
	private CustomSpan span = new CustomSpan();
	
	/**
	 * Creates an empty chip.
	 */
	public MaterialChip() {
		setElement(Document.get().createDivElement());
		setStyleName("chip");
	}
	
	public void setText(String text){
		this.text = text;
		span.setText(text);
		add(span);
	}
	
	public String getText(){
		return text;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
		image.setUrl(url);
		add(image);
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setResource(ImageResource resource) {
		this.resource = resource;
		image.setResource(resource);
		add(image);
	}

	@Override
	public ImageResource getResource() {
		return resource;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public void setIcon(String icon) {
		this.icon.setIcon(icon);
		add(this.icon);
	}

	@Override
	public void setIconPosition(String iconPosition) {
		icon.setIconPosition(iconPosition);
	}

	@Override
	public void setSize(String size) {
		icon.setSize(size);
	}

	@Override
	public void setIconColor(String iconColor) {
		icon.setIconColor(iconColor);
	}

	/**
	 * @return the icon
	 */
	public MaterialIcon getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(MaterialIcon icon) {
		this.icon = icon;
	}
	
}
