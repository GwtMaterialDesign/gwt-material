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
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;
import gwt.material.design.client.base.*;
import gwt.material.design.client.base.mixin.LetterMixin;
import gwt.material.design.client.constants.IconPosition;
import gwt.material.design.client.constants.IconSize;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.html.Span;

//@formatter:off

/**
 * Chips can be used to represent small blocks of information.
 * They are most commonly used either for contacts or for tags.
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code// Simple Chips
 * <m:MaterialChip text="Default" iconType="close"/>
 *
 * // Static Chip
 * <m:MaterialChip text="Apple" />
 *
 * // Contact Chips
 * <m:MaterialChip url="http://b.vimeocdn.com/ps/339/488/3394886_300.jpg" text="Yunalis Mat Zara'ai" iconType="CLOSE"/>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#!chips">Material Chips</a>
 */
//@formatter:on
public class MaterialChip extends MaterialWidget implements HasImage, HasIcon, HasClickHandlers,
        HasAllMouseHandlers, HasDoubleClickHandlers, HasLetter {

    private MaterialIcon icon = new MaterialIcon();
    private Span span = new Span();

    private ImageResource resource;
    private Image image = new Image();

    private final LetterMixin<MaterialChip> letterMixin = new LetterMixin<>(this);

    /**
     * Creates an empty chip.
     */
    public MaterialChip() {
        super(Document.get().createDivElement(), "chip");
    }

    public void setText(String text) {
        span.setText(text);
        add(span);
    }

    public String getText() {
        return span.getElement().getInnerText();
    }

    @Override
    public void setUrl(String url) {
        image.setUrl(url);
        add(image);
    }

    @Override
    public String getUrl() {
        return image.getUrl();
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
    public MaterialIcon getIcon() {
        return icon;
    }

    @Override
    public void setIconType(IconType iconType) {
        icon.setIconType(iconType);
        add(icon);
    }

    @Override
    public void setIconPosition(IconPosition position) {
        icon.setIconPosition(position);
    }

    @Override
    public void setIconSize(IconSize size) {
        icon.setIconSize(size);
    }

    @Override
    public void setIconFontSize(double size, Style.Unit unit) {
        icon.setIconFontSize(size, unit);
    }

    @Override
    public void setIconColor(String iconColor) {
        icon.setIconColor(iconColor);
    }

    @Override
    public void setIconPrefix(boolean prefix) {
        icon.setIconPrefix(prefix);
    }

    @Override
    public boolean isIconPrefix() {
        return icon.isIconPrefix();
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
    public void setLetter(String letter) {
        letterMixin.setLetter(letter);
    }

    @Override
    public String getLetter() {
        return letterMixin.getLetter();
    }

    @Override
    public void setLetterColor(String letterColor) {
        letterMixin.setLetterColor(letterColor);
    }

    @Override
    public void setLetterBackgroundColor(String letterBackgroundColor) {
        letterMixin.setLetterBackgroundColor(letterBackgroundColor);
    }
}
