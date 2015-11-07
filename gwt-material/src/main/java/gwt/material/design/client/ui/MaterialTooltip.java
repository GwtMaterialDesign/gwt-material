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

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.HasOneWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.constants.Position;
import gwt.material.design.client.base.HasId;
import gwt.material.design.client.base.HasPosition;

/**
 * Basic implementation for the Material Design tooltip.
 * <h3>UiBinder Example</h3>
 * <pre>
 *{@code<m:MaterialTooltip text="...">
 *    ...
 * </b:MaterialTooltip>
 *}
 * </pre>
 * @author kevzlou7979
 * @author Ben Dol
 */
public class MaterialTooltip implements IsWidget, HasWidgets, HasOneWidget, HasId, HasText, HasPosition {

    private String text;
    private Position position = Position.TOP;
    private int delayMs = 0;

    private Widget widget;
    private String id;

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
     * @param w widget for the tooltip
     * @param text text for the tooltip
     */
    public MaterialTooltip(final Widget w, final String text) {
        setWidget(w);
        setText(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidget(final Widget w) {
        // Validate
        if (w == widget) {
            return;
        }

        // Detach new child
        if (w != null) {
            w.removeFromParent();
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

        if(!widget.isAttached()) {
            // When we attach it, configure the tooltip
            widget.addAttachHandler(new AttachEvent.Handler() {
                @Override
                public void onAttachOrDetach(final AttachEvent event) {
                    reconfigure();
                }
            });
        } else {
            reconfigure();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final Widget child) {
        if (getWidget() != null) {
            throw new IllegalStateException("Can only contain one child widget");
        }
        setWidget(child);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidget(final IsWidget w) {
        setWidget(w.asWidget());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget getWidget() {
        return widget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final String id) {
        this.id = id;
        if (widget != null) {
            widget.getElement().setId(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getId() {
        return (widget == null) ? id : widget.getElement().getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Position position) {
        this.position = position;

        widget.getElement().setAttribute("data-position", position.getCssName());
    }

    /**
     * {@inheritDoc}
     */
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
        configure(widget.getElement(), text, position.getCssName(), delayMs);
    }

    /**
     * Force the Tooltip to be destroyed
     */
    public void remove() {
        if(widget != null) {
            command(widget.getElement(), "remove");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        widget = null;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Widget asWidget() {
        return widget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return asWidget().toString();
    }

    private native void configure(Element e, String tooltip, String position, int delay) /*-{
        $wnd.jQuery(e).tooltip({
            tooltip: tooltip,
            position: position,
            delay: delay
        });
    }-*/;

    private native void command(final Element e, final String command) /*-{
        $wnd.jQuery(e).tooltip(command);
    }-*/;
}