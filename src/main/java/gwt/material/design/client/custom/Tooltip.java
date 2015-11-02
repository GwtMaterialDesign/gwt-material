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
import gwt.material.design.client.constants.Position;

public class Tooltip {

    private Position position = Position.BOTTOM;
    private int delay = 50;
    private String text = "";

    public Tooltip() {}

    public Tooltip(String text) {
        this.text = text;
    }

    public Tooltip(int delay, String text) {
        this.delay = delay;
        this.text = text;
    }

    public Tooltip(Position position, int delay, String text) {
        this.position = position;
        this.delay = delay;
        this.text = text;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

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
