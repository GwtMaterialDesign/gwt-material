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

import gwt.material.design.client.base.HasCaption;
import gwt.material.design.client.base.HasGrid;
import gwt.material.design.client.constants.ImageType;

import com.google.gwt.user.client.ui.Image;
import gwt.material.design.client.base.HasOpacity;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.GridMixin;

//@formatter:off
/**
 * Images can be styled in different ways using Material Design
 * <h3>UiBinder Usage:</h3>
 *
 * <pre>
 *{@code//Simple Image
 * <m:MaterialImage url="http://assets.materialup.com/uploads/0587e4a8-6a46-4e27-b8bf-836e4350fe82/candycons.gif"/>
 *
 * // Circle Image
 * <m:MaterialImage url="http://assets.materialup.com/uploads/0587e4a8-6a46-4e27-b8bf-836e4350fe82/candycons.gif" type="CIRCLE"/>
 *
 * // MaterialBoxed Image
 * <m:MaterialImage url="http://assets.materialup.com/uploads/0587e4a8-6a46-4e27-b8bf-836e4350fe82/candycons.gif" type="MATERIALBOXED"/>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#media">Material Media</a>
 */
//@formatter:on
public class MaterialImage extends Image implements HasGrid, HasCaption, HasOpacity, HasType<ImageType> {

	private final GridMixin<MaterialImage> gridMixin = new GridMixin<>(this);
	private final CssTypeMixin<ImageType, MaterialImage> typeMixin = new CssTypeMixin<>(this);

	/**
	 * Creates an empty image.
	 */
	public MaterialImage() {
	}

	/**
	 * Creates a simple image.
	 */
	public MaterialImage(String url) {
		this();
		setUrl(url);
	}

	/**
	 * Creates an image with Specific type.
	 */
	public MaterialImage(String url, ImageType type) {
		setUrl(url);
		setType(type);
	}

	@Override
	public void onLoad() {
		super.onLoad();

		addStyleName("responsive-img");
		onInitMaterialDesign();
	}

	@Override
	protected void onUnload() {
		super.onUnload();
	}

	@Override
	public void setType(ImageType type) {
		typeMixin.setType(type);
	}

	@Override
	public ImageType getType() {
		return typeMixin.getType();
	}

	@Override
	public String getCaption() {
		return getElement().getAttribute("caption");
	}

	@Override
	public void setCaption(String caption) {
		getElement().setAttribute("data-caption", caption);
	}

	@Override
	public double getOpacity() {
		return Double.parseDouble(getElement().getStyle().getOpacity());
	}

	@Override
	public void setOpacity(double opacity) {
		getElement().getStyle().setOpacity(opacity);
	}

	@Override
	public void setGrid(String grid) {
		gridMixin.setGrid(grid);
	}

	@Override
	public void setOffset(String offset) {
		gridMixin.setOffset(offset);
	}

	public native void onInitMaterialDesign() /*-{
        $wnd.jQuery(document).ready(function(){
            $wnd.jQuery('.materialboxed').materialbox();
        });
    }-*/;
}
