package gwt.material.design.client.custom;

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

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class Tooltip {

    /**
     * Initialize any uninitialized tooltip elements.
     */
    public static native void detectAndApply() /*-{
        $wnd.jQuery('.tooltipped').tooltip({delay: 50});
    }-*/;

    /**
     * Detect and apply waves, now or when the widget is attached.
     * @param widget target widget to ensure is attached first
     */
    public static void detectAndApply(Widget widget) {
        if(!widget.isAttached()) {
            widget.addAttachHandler(new AttachEvent.Handler() {
                @Override
                public void onAttachOrDetach(AttachEvent event) {
                    if(event.isAttached()) {
                        detectAndApply();
                    }
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
