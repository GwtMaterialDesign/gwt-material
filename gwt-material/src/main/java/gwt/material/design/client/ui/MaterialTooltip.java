/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.*;
import gwt.material.design.client.base.HasId;
import gwt.material.design.client.base.HasPosition;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.events.DefaultHandlerRegistry;
import gwt.material.design.client.js.JsTooltipOptions;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static gwt.material.design.client.js.JsMaterialElement.$;

/**
 * Basic implementation for the Material Design tooltip.
 * <h3>UiBinder Example</h3>
 * <pre>
 * {@code
 * <m:MaterialTooltip text="...">
 *    ...
 * </b:MaterialTooltip>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @author Ben Dol
 *
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#dialogs">Material Tooltip</a>
 * @see <a href="https://material.io/guidelines/components/tooltips.html">Material Design Specification</a>
 */
public class MaterialTooltip implements IsWidget, HasWidgets, HasOneWidget, HasId, HasText, HasPosition {

    private String text;
    private Position position = Position.TOP;
    private int delayMs = 0;

    private Widget widget;
    private String id;
    private String html;
    private DefaultHandlerRegistry handlerRegistry;

    /**
     * Creates the empty Tooltip
     */
    public MaterialTooltip() {
    }

    /**
     * Creates the tooltip around this widget
     *
     * @param w widget for the tooltip
     */
    public MaterialTooltip(final Widget w) {
        setWidget(w);
    }

    /**
     * Creates the tooltip around this widget with given title
     *
     * @param w    widget for the tooltip
     * @param text text for the tooltip
     */
    public MaterialTooltip(final Widget w, final String text) {
        setWidget(w);
        setText(text);
    }

    @Override
    public void setWidget(final Widget widget) {
        handlerRegistry = new DefaultHandlerRegistry(widget);
        // Validate
        if (widget == this.widget) {
            return;
        }

        // Remove old child
        if (this.widget != null) {
            remove(this.widget);
        }

        // Logical attach, but don't physical attach; done by jquery.
        this.widget = widget;
        if (this.widget == null) {
            return;
        }

        if (!this.widget.isAttached()) {
            // When we attach it, configure the tooltip
            handlerRegistry.registerHandler(widget.addAttachHandler(event -> {
                if(event.isAttached()) {
                    reinitialize();
                } else {
                    remove();
                }
            }));
        } else {
            // ensure the tooltip is removed on detachment
            handlerRegistry.registerHandler(widget.addAttachHandler(event -> {
                if(!event.isAttached()) {
                    remove();
                }
            }));
            reinitialize();
        }
    }

    @Override
    public void add(final Widget child) {
        if (getWidget() != null) {
            throw new IllegalStateException("Can only contain one child widget");
        }
        setWidget(child);
    }

    @Override
    public void setWidget(final IsWidget w) {
        setWidget(w.asWidget());
    }

    @Override
    public Widget getWidget() {
        return widget;
    }

    @Override
    public void setId(final String id) {
        this.id = id;
        if (widget != null) {
            widget.getElement().setId(id);
        }
    }

    @Override
    public String getId() {
        return (widget == null) ? id : widget.getElement().getId();
    }

    @Override
    public void setPosition(final Position position) {
        this.position = position;

        widget.getElement().setAttribute("data-position", position.getCssName());
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setDelayMs(final int delayMs) {
        this.delayMs = delayMs;

        widget.getElement().setAttribute("data-delay", String.valueOf(delayMs));
    }

    public int getDelayMs() {
        return delayMs;
    }

    /**
     * Gets the tooltip's display string
     *
     * @return String tooltip display string
     */
    @Override
    public String getText() {
        return text;
    }

    /**
     * Sets the tooltip's display string
     *
     * @param text String display string
     */
    @Override
    public void setText(final String text) {
        this.text = text;

        widget.getElement().setAttribute("data-tooltip", text);
    }

    /**
     * Reconfigures the tooltip, must be called when altering
     * any tooltip after it has already been shown.
     */
    public void reinitialize() {
        remove();
        initialize();
    }


    protected void initialize() {
        initialize(text, position.getCssName(), delayMs);
    }

    protected void initialize(String tooltip, String position, int delay) {
        JsTooltipOptions options = new JsTooltipOptions();
        options.tooltip = tooltip;
        options.position = position;
        options.delay = delay;
        $(widget.getElement()).tooltip(options);
    }

    /**
     * Force the Tooltip to be destroyed
     */
    public void remove() {
        if (widget != null) {
            command("remove");
        }
    }

    @Override
    public void clear() {
        widget = null;
    }

    @Override
    public Iterator<Widget> iterator() {
        // Simple iterator for the widget
        return new Iterator<Widget>() {
            boolean hasElement = widget != null;
            Widget returned = null;

            @Override
            public boolean hasNext() {
                return hasElement;
            }

            @Override
            public Widget next() {
                if (!hasElement || (widget == null)) {
                    throw new NoSuchElementException();
                }
                hasElement = false;
                return (returned = widget);
            }

            @Override
            public void remove() {
                if (returned != null) {
                    MaterialTooltip.this.remove(returned);
                }
            }
        };
    }

    @Override
    public boolean remove(final Widget w) {
        // Validate.
        if (widget != w) {
            return false;
        }

        // Logical detach.
        clear();
        return true;
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    @Override
    public String toString() {
        return asWidget().toString();
    }

    protected void command(String command) {
        $(widget.getElement()).tooltip(command);
    }

    /**
     * @deprecated Use {@link #getHtml}
     */
    @Deprecated
    public String getTooltipHTML() {
        return getHtml();
    }

    /**
     * @deprecated Use {@link #setHtml}
     */
    @Deprecated
    public void setTooltipHTML(String html) {
        setHtml(html);
    }

    /**
     * Get the html of the tooltip.
     */
    public String getHtml() {
        return html;
    }

    /**
     * Set the html as value inside the tooltip.
     */
    public void setHtml(String html) {
        this.html = html;

        Element element = widget.getElement();
        if (widget.isAttached()) {
            $("#" + element.getAttribute("data-tooltip-id"))
                .find("span")
                .html(html != null ? html : "");
        } else {
            handlerRegistry.registerHandler(widget.addAttachHandler(attachEvent -> $("#" + element.getAttribute("data-tooltip-id"))
                    .find("span")
                    .html(html != null ? html : "")));
        }
    }
}