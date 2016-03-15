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
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import gwt.material.design.client.base.HasCaption;
import gwt.material.design.client.base.HasImage;
import gwt.material.design.client.base.HasType;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.CssTypeMixin;
import gwt.material.design.client.base.mixin.ImageMixin;
import gwt.material.design.client.constants.ImageType;

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
public class MaterialImage extends MaterialWidget implements HasCaption, HasType<ImageType>, HasImage, HasClickHandlers,
        HasDoubleClickHandlers, HasAllMouseHandlers, HasLoadHandlers, HasErrorHandlers, HasAllDragAndDropHandlers,
        HasAllGestureHandlers, HasAllTouchHandlers {

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

    public int getWidth() {
        ImageElement imageElement = getElement().cast();
        return imageElement.getWidth();
    }

    public int getHeight() {
        ImageElement imageElement = getElement().cast();
        return imageElement.getHeight();
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

    @Override
    public HandlerRegistration addLoadHandler(LoadHandler handler) {
        return addDomHandler(handler, LoadEvent.getType());
    }

    @Override
    public HandlerRegistration addErrorHandler(ErrorHandler handler) {
        return addDomHandler(handler, ErrorEvent.getType());
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
        return addDomHandler(handler, DoubleClickEvent.getType());

    }

    @Override
    public HandlerRegistration addDragEndHandler(DragEndHandler handler) {
        return addBitlessDomHandler(handler, DragEndEvent.getType());
    }

    @Override
    public HandlerRegistration addDragEnterHandler(DragEnterHandler handler) {
        return addBitlessDomHandler(handler, DragEnterEvent.getType());
    }

    @Override
    public HandlerRegistration addDragHandler(DragHandler handler) {
        return addBitlessDomHandler(handler, DragEvent.getType());
    }

    @Override
    public HandlerRegistration addDragLeaveHandler(DragLeaveHandler handler) {
        return addBitlessDomHandler(handler, DragLeaveEvent.getType());
    }

    @Override
    public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
        return addBitlessDomHandler(handler, DragOverEvent.getType());
    }

    @Override
    public HandlerRegistration addDragStartHandler(DragStartHandler handler) {
        return addBitlessDomHandler(handler, DragStartEvent.getType());
    }

    @Override
    public HandlerRegistration addDropHandler(DropHandler handler) {
        return addBitlessDomHandler(handler, DropEvent.getType());
    }

    @Override
    public HandlerRegistration addGestureChangeHandler(GestureChangeHandler handler) {
        return addDomHandler(handler, GestureChangeEvent.getType());
    }

    @Override
    public HandlerRegistration addGestureEndHandler(GestureEndHandler handler) {
        return addDomHandler(handler, GestureEndEvent.getType());
    }

    @Override
    public HandlerRegistration addGestureStartHandler(GestureStartHandler handler) {
        return addDomHandler(handler, GestureStartEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchCancelHandler(TouchCancelHandler handler) {
        return addDomHandler(handler, TouchCancelEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchEndHandler(TouchEndHandler handler) {
        return addDomHandler(handler, TouchEndEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler) {
        return addDomHandler(handler, TouchMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
        return addDomHandler(handler, TouchStartEvent.getType());
    }
}
