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

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.js.JsPushpinOptions;

import static gwt.material.design.client.js.JsMaterialElement.$;
//@formatter:off

/**
 * Pushpin is our fixed positioning plugin. You can check out our live examples: the fixed Table of Contents on the right.
 * <h3>UiBinder Usage:</h3>
 * <p>
 * <pre>
 * {@code
 * // CAN BE CALLED AS A HELPER STATIC CONTEXT
 * MaterialPushpin.apply(target, source.getOffsetHeight() + 600);
 *
 * // INSTANTIATE THE PUSHPIN COMPONENT
 * MaterialPushpin pushpin = new MaterialPushpin();
 * pushpin.setWidget(source);
 * pushpin.setTop(300.0);
 * pushpin.setOffset(64.0);
 * pushpin.setBottom(500.0);
 * pushpin.apply();
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#pushpin">Material PushPin</a>
 */
public class MaterialPushpin {

    private Widget widget;
    private Double offset;
    private Double top;
    private Double bottom;

    public MaterialPushpin() {}

    /**
     * Apply the pushpin feature into the target widget
     */
    public void apply() {
        Scheduler.get().scheduleDeferred(() -> {
            JsPushpinOptions options = new JsPushpinOptions();
            options.top = (top != null) ? top : 0;
            options.offset = (offset != null) ? offset : 0;
            options.bottom = (bottom != null) ? bottom : "Infinity";
            if (widget != null) {
                $(widget.getElement()).pushpin(options);
            } else {
                GWT.log("Please set your widget before applying the pushpin", new IllegalStateException());
            }
        });
    }

    /**
     * A pushpinned element has 3 states. One above and below the scrolling threshold,
     * and the pinned state where the element becomes fixed.
     *
     * @param widget - Target widget to apply the pushpin feature
     * @param offset - The offset from the top the element will be fixed at. (Default: 0)
     */
    public static void apply(Widget widget, Double offset) {
        apply(widget, null, null, offset);
    }

    /**
     * A pushpinned element has 3 states. One above and below the scrolling threshold,
     * and the pinned state where the element becomes fixed.
     *
     * @param widget - Target widget to apply the pushpin feature
     * @param top    - The distance in pixels from the top of the page where the element becomes fixed. (Default: 0)
    * @param offset - The offset from the top the element will be fixed at. (Default: 0)
     */
    public static void apply(Widget widget, Double top, Double offset) {
        apply(widget, top, null, offset);
    }

    /**
     * A pushpinned element has 3 states. One above and below the scrolling threshold,
     * and the pinned state where the element becomes fixed.
     *
     * @param widget - Target widget to apply the pushpin feature
     * @param top    - The distance in pixels from the top of the page where the element becomes fixed. (Default: 0)
     * @param bottom - The distance in pixels from the top of the page where the elements stops being fixed. (Default: Infinity)
     * @param offset - The offset from the top the element will be fixed at. (Default: 0)
     */
    public static void apply(Widget widget, Double top, Double bottom, Double offset) {
        MaterialPushpin pushpin = new MaterialPushpin();
        pushpin.setWidget(widget);
        pushpin.setTop(top);
        pushpin.setBottom(bottom);
        pushpin.setOffset(offset);
        pushpin.apply();
    }

    /**
     * Get the target widget which the pushpin was applied
     */
    public Widget getWidget() {
        return widget;
    }

    /**
     * Set the target widget to apply the pushpin feature
     */
    public void setWidget(Widget widget) {
        this.widget = widget;
    }

    /**
     * Get the offset from the top the element will be fixd at.
     */
    public Double getOffset() {
        return offset;
    }

    /**
     * Set the offset from the top the element will be fixed at. (Default: 0)
     */
    public void setOffset(Double offset) {
        this.offset = offset;
    }

    /**
     * Get the distance in pixels from the top of the page where the element becomes fixed. (Default: 0)
     * @return
     */
    public Double getTop() {
        return top;
    }

    /**
     * Set the distance in pixels from the top of the page where the element becomes fixed. (Default: 0)
     */
    public void setTop(Double top) {
        this.top = top;
    }

    /**
     * Get the distance in pixels from the top of the page where the elements stops being fixed. (Default: Infinity)
     */
    public Double getBottom() {
        return bottom;
    }

    /**
     * Set the distance in pixels from the top of the page where the elements stops being fixed. (Default: Infinity)
     */
    public void setBottom(Double bottom) {
        this.bottom = bottom;
    }
}
