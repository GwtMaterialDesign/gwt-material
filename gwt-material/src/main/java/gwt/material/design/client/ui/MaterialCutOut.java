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

import gwt.material.design.client.base.ComplexWidget;
import gwt.material.design.client.base.HasCircle;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.event.logical.shared.HasCloseHandlers;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
 * MaterialCutOut is a fullscreen modal-like component to show users about new
 * features or important elements of the document.
 * 
 * You can use {@link CloseHandler}s to be notified when the cut out is closed.
 * 
 * <h3>UiBinder Usage:</h3>
 * 
 * <pre>
 * {@code
 * <m:MaterialCutOut ui:field="cutOut">
 *      <!-- add any widgets here -->
 * </m:MaterialCutOut>
 * }
 * </pre>
 * 
 * <h3>Java Usage:</h3>
 * {@code
 * MaterialCutOut cutOut = ... //create using new or using UiBinder
 * cutOut.setTarget(myTargetWidget); //the widget or element you want to focus
 * cutOut.openCutOut(); //shows the modal over the page
 * }
 * 
 * <h3>Custom styling:</h3> You use change the cut out style by using the
 * <code>material-cutout</code> class, and <code>material-cutout-focus</code>
 * class for the focus box.
 * 
 * @author gilberto-torrezan
 *
 */
// @formatter:on
public class MaterialCutOut extends ComplexWidget implements HasCloseHandlers<MaterialCutOut>, HasCircle {

    private String backgroundColor = "blue";
    private double opacity = 0.8;
    
    private String computedBackgroundColor;
    private int cutOutPadding = 10;
    private boolean circle = false;
    private boolean autoAddedToDocument = false;
    private Element targetElement;
    private Element focus;

    public MaterialCutOut() {
        super(Document.get().createDivElement());
        focus = Document.get().createDivElement();
        focus.setId(DOM.createUniqueId());
        getElement().appendChild(focus);

        setStyleName("material-cutout");
        Style style = getElement().getStyle();
        style.setWidth(100, Unit.PCT);
        style.setHeight(100, Unit.PCT);
        style.setPosition(Position.FIXED);
        style.setTop(0, Unit.PX);
        style.setLeft(0, Unit.PX);
        style.setOverflow(Overflow.HIDDEN);
        style.setZIndex(10000);
        style.setDisplay(Display.NONE);

        focus.setClassName("material-cutout-focus");
        style = focus.getStyle();
        style.setProperty("content", "\'\'");
        style.setPosition(Position.ABSOLUTE);
        style.setZIndex(-1);
    }
    
    @Override
    public void setBackgroundColor(String bgColor) {
        backgroundColor = bgColor;
        setupComputedBackgroundColor();
    }
    
    @Override
    public String getBackgroundColor() {
        return backgroundColor;
    }
    
    @Override
    public void setOpacity(double opacity) {
        this.opacity = opacity;
        setupComputedBackgroundColor();
    }
    
    @Override
    public double getOpacity() {
        return opacity;
    }

    /**
     * Sets if the cut out should be rendered as a circle or a simple rectangle.
     * Circle is better for targets with same width and height. The default is
     * <code>false</code> (is a rectangle).
     */
    @Override
    public void setCircle(boolean circle) {
        this.circle = circle;
    }

    /**
     * @return The if the cut out should be rendered as a circle or a simple
     *         rectangle
     */
    @Override
    public boolean isCircle() {
        return circle;
    }

    /**
     * Sets the padding in pixels of the cut out focus in relation to the target
     * element. The default is 10.
     */
    public void setCutOutPadding(int cutOutPadding) {
        this.cutOutPadding = cutOutPadding;
    }

    /**
     * @return The padding in pixels of the cut out focus in relation to the
     *         target element
     */
    public int getCutOutPadding() {
        return cutOutPadding;
    }

    /**
     * Sets the target element to be focused by the cut out.
     */
    public void setTarget(Element targetElement) {
        this.targetElement = targetElement;
    }
    
    /**
     * Sets the target widget to be focused by the cut out. Its the same as
     * calling setTarget(widget.getElement());
     * 
     * @see #setTarget(Element)
     */
    public void setTarget(Widget widget) {
        setTarget(widget.getElement());
    }

    /**
     * @return The target element to be focused
     */
    public Element getTargetElement() {
        return targetElement;
    }

    /**
     * Opens the modal cut out taking all the screen. The target element should
     * be set before calling this method.
     * 
     * @throws IllegalStateException
     *             if the target element is <code>null</code>
     * @see #setTargetElement(Element)
     */
    public void openCutOut() {
        if (targetElement == null) {
            throw new IllegalStateException("The target element should be set before calling openCutOut().");
        }
        targetElement.scrollIntoView();

        if (computedBackgroundColor == null){
            setupComputedBackgroundColor();
        }
        
        String focusId = focus.getId();
        setupAnimation(focusId + "-animation", computedBackgroundColor);
        focus.getStyle().setProperty("animation", focusId + "-animation 0.5s ease forwards");

        if (circle) {
            focus.getStyle().setProperty("borderRadius", "50%");
        } else {
            focus.getStyle().clearProperty("borderRadius");
        }
        setupCutOutPosition(focus, targetElement, cutOutPadding);
        
        //disables scrolling
        Window.enableScrolling(false);

        Window.addResizeHandler(new ResizeHandler() {
            @Override
            public void onResize(ResizeEvent event) {
                setupCutOutPosition(focus, targetElement, cutOutPadding);
            }
        });
        Window.addWindowScrollHandler(new ScrollHandler() {
            @Override
            public void onWindowScroll(ScrollEvent event) {
                setupCutOutPosition(focus, targetElement, cutOutPadding);
            }
        });
        getElement().getStyle().clearDisplay();

        // verify if the component is added to the document (via UiBinder for
        // instance)
        if (getParent() == null) {
            autoAddedToDocument = true;
            RootPanel.get().add(this);
        }
    }

    /**
     * Closes the cut out. It is the same as calling
     * {@link #closeCutOut(boolean)} with <code>false</code>.
     */
    public void closeCutOut() {
        this.closeCutOut(false);
    }

    /**
     * Closes the cut out.
     * 
     * @param autoClosed
     *            Notifies with the modal was auto closed or closed by user
     *            action
     */
    public void closeCutOut(boolean autoClosed) {
        //re-enables scrolling
        Window.enableScrolling(true);
        
        getElement().getStyle().setDisplay(Display.NONE);

        // if the component added himself to the document, it must remove
        // himself too
        if (autoAddedToDocument) {
            this.removeFromParent();
            autoAddedToDocument = false;
        }
        CloseEvent.fire(this, this, autoClosed);
    }
    
    /**
     * Setups the cut out position when the screen changes size or is scrolled.
     */
    private native void setupCutOutPosition(Element cutOut, Element relativeTo, int padding)/*-{
        var rect = relativeTo.getBoundingClientRect();
        
        var top = rect.top;
        var left = rect.left;
        var width = rect.right - rect.left;
        var height = rect.bottom - rect.top; 
        
        top -= padding;
        left -= padding;
        width += padding * 2;
        height += padding * 2;
        
        cutOut.style.top = top + 'px';
        cutOut.style.left = left + 'px';
        cutOut.style.width = width + 'px';
        cutOut.style.height = height + 'px';
    }-*/;
    
    /**
     * Creates the CSS animation of the opening cut out.
     */
    private native void setupAnimation(String animationId, String color)/*-{
        var sheet = $doc.styleSheets[0];
        
        var animationSelector = '@keyframes '+animationId;
        var animationFrames = '{' +
             'from {box-shadow: 0px 0px 0px 0rem '+color+';}' +
             'to {box-shadow: 0px 0px 0px 100rem '+color+';}' +
        '}'
        sheet.insertRule(animationSelector + animationFrames, 0);
    }-*/;
    
    /**
     * Gets the computed background color, based on the backgroundColor CSS class.
     */
    private void setupComputedBackgroundColor() {
        // temp is just a widget created to evaluate the computed background
        // color
        ComplexWidget temp = new ComplexWidget(Document.get().createDivElement());
        temp.setBackgroundColor(backgroundColor);
        computedBackgroundColor = getComputedBackgroundColor(temp.getElement()).toLowerCase();

        // convert rgb to rgba, considering the opacity field
        if (opacity < 1 && computedBackgroundColor.startsWith("rgb(")) {
            computedBackgroundColor = computedBackgroundColor.replace("rgb(", "rgba(").replace(")",
                ", " + opacity + ")");
        }
    }
    
    /**
     * Native call to getComputedStyle.
     */
    private native String getComputedBackgroundColor(Element e)/*-{
        var cs = $wnd.document.defaultView.getComputedStyle(e, null);
        return cs.getPropertyValue('background-color');
    }-*/;
    
    @Override
    public HandlerRegistration addCloseHandler(CloseHandler<MaterialCutOut> handler) {
        return addHandler(handler, CloseEvent.getType());
    }

}
