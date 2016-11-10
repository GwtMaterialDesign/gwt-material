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
 * MaterialPushpin.apply(target, source.getOffsetHeight() + 600);
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#!pushpin">Material PushPin</a>
 */
public class MaterialPushpin {

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
        Scheduler.get().scheduleDeferred(() -> {
            JsPushpinOptions options = new JsPushpinOptions();
            options.top = (top != null) ? top : 0;
            options.offset = (offset != null) ? offset : 0;
            options.bottom = (bottom != null) ? bottom : "Infinity";

            GWT.log(top + " : Top");
            GWT.log(offset + " : Offset");
            GWT.log(bottom + " : Bottom");

            $(widget.getElement()).pushpin(options);
        });
    }
}
