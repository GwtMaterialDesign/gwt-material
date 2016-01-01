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

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.ImageMixin;
import gwt.material.design.client.constants.ImageType;

import com.google.gwt.user.client.ui.Image;
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
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#media">Material Media</a>
 */
//@formatter:on
public class MaterialImage extends MaterialWidget implements HasCaption, HasType<ImageType>, HasImage, HasClickHandlers, HasAllMouseHandlers {

    private final CssTypeMixin<ImageType, MaterialImage> typeMixin = new CssTypeMixin<>(this);
    private final ImageMixin<MaterialImage> imageMixin = new ImageMixin<>(this);
    /**
     * Creates an empty image.
     */
    public MaterialImage() {
        super(Document.get().createImageElement());
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

    public native void onInitMaterialDesign() /*-{
        $wnd.jQuery(document).ready(function(){
            $wnd.jQuery('.materialboxed').materialbox();
        });
    }-*/;

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

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
        return addDomHandler(handler, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
        return addDomHandler(handler, MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
        return addDomHandler(handler, MouseMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
        return addDomHandler(handler, MouseOutEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
        return addDomHandler(handler, MouseOverEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
        return addDomHandler(handler, MouseUpEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
        return addDomHandler(handler, MouseWheelEvent.getType());
    }
}
