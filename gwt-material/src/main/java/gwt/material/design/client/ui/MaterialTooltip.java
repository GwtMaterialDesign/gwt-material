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

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.*;
import gwt.material.design.client.base.HasId;
import gwt.material.design.client.base.HasPosition;
import gwt.material.design.client.constants.Position;
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
 */
public class MaterialTooltip implements IsWidget, HasWidgets, HasOneWidget, HasId, HasText, HasPosition {

    private String text;
    private Position position = Position.TOP;
    private int delayMs = 0;

    private Widget widget;
    private String id;
    private String html;

    private HandlerRegistration attachHandler;
    private HandlerRegistration htmlAttachHandler;

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
    public void setWidget(final Widget w) {
        // Validate
        if (w == widget) {
            return;
        }

        if (attachHandler != null) {
            attachHandler.removeHandler();
            attachHandler = null;
        }

        // Remove old child
        if (widget != null) {
            remove(widget);
        }

        // Logical attach, but don't physical attach; done by jquery.
        widget = w;
        if (widget == null) {
            return;
        }

        if (!widget.isAttached()) {
            // When we attach it, configure the tooltip
            attachHandler = widget.addAttachHandler(event -> {
                if(event.isAttached()) {
                    reconfigure();
                } else {
                    remove();
                }
            });
        } else {
            // ensure the tooltip is removed on detachment
            attachHandler = widget.addAttachHandler(event -> {
                if(!event.isAttached()) {
                    remove();
                }
            });
            reconfigure();
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
    public void reconfigure() {
        remove();
        configure();
    }

    protected void configure() {
        configure(text, position.getCssName(), delayMs);
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

    protected void configure(String tooltip, String position, int delay) {
        JsTooltipOptions options = new JsTooltipOptions();
        options.tooltip = tooltip;
        options.position = position;
        options.delay = delay;
        $(widget.getElement()).tooltip(options);
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
        if (htmlAttachHandler != null) {
            htmlAttachHandler.removeHandler();
            htmlAttachHandler = null;
        }

        Element element = widget.getElement();
        if (widget.isAttached()) {
            $("#" + element.getAttribute("data-tooltip-id"))
                .find("span")
                .html(html != null ? html : "");
        } else {
            htmlAttachHandler = widget.addAttachHandler(attachEvent -> {
                $("#" + element.getAttribute("data-tooltip-id"))
                    .find("span")
                    .html(html != null ? html : "");
            });
        }
    }
}