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
package gwt.material.design.client.base;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.client.js.JsMaterialElement;

public class Waves {

    public static final String WAVES_STYLE = "waves-effect";

    /**
     * Initialize any uninitialized wave elements.
     */
    public static void detectAndApply() {
        JsMaterialElement.displayEffect();
    }

    /**
     * Detect and apply waves, now or when the widget is attached.
     *
     * @param widget target widget to ensure is attached first
     */
    public static void detectAndApply(Widget widget) {
        if (!widget.isAttached()) {
            widget.addAttachHandler(event -> {
                if (event.isAttached()) {
                    detectAndApply();
                }
            });
        } else {
            detectAndApply();
        }
    }

    public static void detectAndApply(IsWidget isWidget) {
        detectAndApply(isWidget.asWidget());
    }
}
