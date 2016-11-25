/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.ActivatesMixin;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.ImageMixin;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.constants.ImageType;

import static gwt.material.design.client.js.JsMaterialElement.$;

//@formatter:off

/**
 * Images can be styled in different ways using Material Design
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code //Simple Image
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
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#media">Material Media</a>
 */
//@formatter:on
public class MaterialImage extends MaterialWidget implements HasCaption, HasType<ImageType>, HasImage,
        HasLoadHandlers, HasErrorHandlers, HasActivates {

    private final CssTypeMixin<ImageType, MaterialImage> typeMixin = new CssTypeMixin<>(this);
    private final ImageMixin<MaterialImage> imageMixin = new ImageMixin<>(this);
    private final ActivatesMixin<MaterialImage> activatesMixin = new ActivatesMixin<>(this);

    /**
     * Creates an empty image.
     */
    public MaterialImage() {
        super(Document.get().createImageElement(), CssName.RESPONSIVE_IMG);
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
        this(url);
        setType(type);
    }

    /**
     * Creates an image from an ImageResource.
     */
    public MaterialImage(ImageResource resource) {
        this();
        setResource(resource);
    }

    /**
     * Creates an image from an ImageResource with Specific type.
     */
    public MaterialImage(ImageResource resource, ImageType type) {
        this(resource);
        setType(type);
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        $(".materialboxed").materialbox();
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
        return getElement().getAttribute("data-caption");
    }

    @Override
    public void setCaption(String caption) {
        getElement().setAttribute("data-caption", caption);
    }

    @Override
    public void setUrl(String url) {
        imageMixin.setUrl(url);
    }

    @Override
    public String getUrl() {
        return imageMixin.getUrl();
    }

    @Override
    public void setResource(ImageResource resource) {
        imageMixin.setResource(resource);
    }

    @Override
    public ImageResource getResource() {
        return imageMixin.getResource();
    }

    public int getWidth() {
        return ((ImageElement)getElement().cast()).getWidth();
    }

    public int getHeight() {
        return ((ImageElement)getElement().cast()).getHeight();
    }

    @Override
    public HandlerRegistration addLoadHandler(final LoadHandler handler) {
        return addDomHandler(handler, LoadEvent.getType());
    }

    @Override
    public HandlerRegistration addErrorHandler(final ErrorHandler handler) {
        return addDomHandler(handler, ErrorEvent.getType());
    }

    @Override
    public void setActivates(String activates) {
        activatesMixin.setActivates(activates);
    }

    @Override
    public String getActivates() {
        return activatesMixin.getActivates();
    }
}
