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
        super(Document.get().createImageElement(), "responsive-img");
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
        $wnd.jQuery(document).ready(function() {
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
    public HandlerRegistration addClickHandler(final ClickHandler handler) {
        return addDomHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if(isEnabled()){
                    handler.onClick(event);
                }
            }
        }, ClickEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
        return addDomHandler(new MouseDownHandler() {
            @Override
            public void onMouseDown(MouseDownEvent event) {
                if(isEnabled()){
                    handler.onMouseDown(event);
                }
            }
        }, MouseDownEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
        return addDomHandler(new MouseMoveHandler() {
            @Override
            public void onMouseMove(MouseMoveEvent event) {
                if(isEnabled()){
                    handler.onMouseMove(event);
                }
            }
        }, MouseMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
        return addDomHandler(new MouseOutHandler() {
            @Override
            public void onMouseOut(MouseOutEvent event) {
                if(isEnabled()){
                    handler.onMouseOut(event);
                }
            }
        }, MouseOutEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
        return addDomHandler(new MouseOverHandler() {
            @Override
            public void onMouseOver(MouseOverEvent event) {
                if(isEnabled()){
                    handler.onMouseOver(event);
                }
            }
        }, MouseOverEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
        return addDomHandler(new MouseUpHandler() {
            @Override
            public void onMouseUp(MouseUpEvent event) {
                if(isEnabled()){
                    handler.onMouseUp(event);
                }
            }
        }, MouseUpEvent.getType());
    }

    @Override
    public HandlerRegistration addMouseWheelHandler(final MouseWheelHandler handler) {
        return addDomHandler(new MouseWheelHandler() {
            @Override
            public void onMouseWheel(MouseWheelEvent event) {
                if(isEnabled()){
                    handler.onMouseWheel(event);
                }
            }
        }, MouseWheelEvent.getType());
    }

    @Override
    public HandlerRegistration addLoadHandler(final LoadHandler handler) {
        return addDomHandler(new LoadHandler() {
            @Override
            public void onLoad(LoadEvent event) {
                if(isEnabled()){
                    handler.onLoad(event);
                }
            }
        }, LoadEvent.getType());
    }

    @Override
    public HandlerRegistration addErrorHandler(final ErrorHandler handler) {
        return addDomHandler(new ErrorHandler() {
            @Override
            public void onError(ErrorEvent event) {
                if(isEnabled()){
                    handler.onError(event);
                }
            }
        }, ErrorEvent.getType());
    }

    @Override
    public HandlerRegistration addDoubleClickHandler(final DoubleClickHandler handler) {
        return addDomHandler(new DoubleClickHandler() {
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
                if(isEnabled()){
                    handler.onDoubleClick(event);
                }
            }
        }, DoubleClickEvent.getType());

    }

    @Override
    public HandlerRegistration addDragEndHandler(final DragEndHandler handler) {
        return addBitlessDomHandler(new DragEndHandler() {
            @Override
            public void onDragEnd(DragEndEvent event) {
                if(isEnabled()){
                    handler.onDragEnd(event);
                }
            }
        }, DragEndEvent.getType());
    }

    @Override
    public HandlerRegistration addDragEnterHandler(final DragEnterHandler handler) {
        return addBitlessDomHandler(new DragEnterHandler() {
            @Override
            public void onDragEnter(DragEnterEvent event) {
                if(isEnabled()){
                    handler.onDragEnter(event);
                }
            }
        }, DragEnterEvent.getType());
    }

    @Override
    public HandlerRegistration addDragHandler(final DragHandler handler) {
        return addBitlessDomHandler(new DragHandler() {
            @Override
            public void onDrag(DragEvent event) {
                if(isEnabled()){
                    handler.onDrag(event);
                }
            }
        }, DragEvent.getType());
    }

    @Override
    public HandlerRegistration addDragLeaveHandler(final DragLeaveHandler handler) {
        return addBitlessDomHandler(new DragLeaveHandler() {
            @Override
            public void onDragLeave(DragLeaveEvent event) {
                if(isEnabled()){
                    handler.onDragLeave(event);
                }
            }
        }, DragLeaveEvent.getType());
    }

    @Override
    public HandlerRegistration addDragOverHandler(final DragOverHandler handler) {
        return addBitlessDomHandler(new DragOverHandler() {
            @Override
            public void onDragOver(DragOverEvent event) {
                if(isEnabled()){
                    handler.onDragOver(event);
                }
            }
        }, DragOverEvent.getType());
    }

    @Override
    public HandlerRegistration addDragStartHandler(final DragStartHandler handler) {
        return addBitlessDomHandler(new DragStartHandler() {
            @Override
            public void onDragStart(DragStartEvent event) {
                if(isEnabled()){
                    handler.onDragStart(event);
                }
            }
        }, DragStartEvent.getType());
    }

    @Override
    public HandlerRegistration addDropHandler(final DropHandler handler) {
        return addBitlessDomHandler(new DropHandler() {
            @Override
            public void onDrop(DropEvent event) {
                if(isEnabled()){
                    handler.onDrop(event);
                }
            }
        }, DropEvent.getType());
    }

    @Override
    public HandlerRegistration addGestureChangeHandler(final GestureChangeHandler handler) {
        return addDomHandler(new GestureChangeHandler() {
            @Override
            public void onGestureChange(GestureChangeEvent event) {
                if(isEnabled()){
                    handler.onGestureChange(event);
                }
            }
        }, GestureChangeEvent.getType());
    }

    @Override
    public HandlerRegistration addGestureEndHandler(final GestureEndHandler handler) {
        return addDomHandler(new GestureEndHandler() {
            @Override
            public void onGestureEnd(GestureEndEvent event) {
                if(isEnabled()){
                    handler.onGestureEnd(event);
                }
            }
        }, GestureEndEvent.getType());
    }

    @Override
    public HandlerRegistration addGestureStartHandler(final GestureStartHandler handler) {
        return addDomHandler(new GestureStartHandler() {
            @Override
            public void onGestureStart(GestureStartEvent event) {
                if(isEnabled()){
                    handler.onGestureStart(event);
                }
            }
        }, GestureStartEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchCancelHandler(final TouchCancelHandler handler) {
        return addDomHandler(new TouchCancelHandler() {
            @Override
            public void onTouchCancel(TouchCancelEvent event) {
                if(isEnabled()){
                    handler.onTouchCancel(event);
                }
            }
        }, TouchCancelEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchEndHandler(final TouchEndHandler handler) {
        return addDomHandler(new TouchEndHandler() {
            @Override
            public void onTouchEnd(TouchEndEvent event) {
                if(isEnabled()){
                    handler.onTouchEnd(event);
                }
            }
        }, TouchEndEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchMoveHandler(final TouchMoveHandler handler) {
        return addDomHandler(new TouchMoveHandler() {
            @Override
            public void onTouchMove(TouchMoveEvent event) {
                if(isEnabled()){
                    handler.onTouchMove(event);
                }
            }
        }, TouchMoveEvent.getType());
    }

    @Override
    public HandlerRegistration addTouchStartHandler(final TouchStartHandler handler) {
        return addDomHandler(new TouchStartHandler() {
            @Override
            public void onTouchStart(TouchStartEvent event) {
                if(isEnabled()){
                    handler.onTouchStart(event);
                }
            }
        }, TouchStartEvent.getType());
    }
}
