/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2019 GwtMaterialDesign
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
package gwt.material.design.client.base.viewport;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.js.Window;
import gwt.material.design.jquery.client.api.Functions;

public class DefaultViewPortResizeHandler implements ViewPortResizeHandler {

    public static final String RESIZE_ANIMATION_STOPPER = "resize-animation-stopper";

    protected HandlerRegistration resize;
    protected ResizeEvent event;
    protected Integer resizingDelayMillis = 250;

    @Override
    public void load(Functions.Func1<ResizeEvent> callback) {
        // Optimize Window Resizing by just executing the callback once resizing is done.
        Timer timer = new Timer() {
            @Override
            public void run() {
                callback.call(event);
            }
        };

        resize = Window.addResizeHandler(event -> {
            this.event = event;

            timer.schedule(resizingDelayMillis);

            // Optimize the Resizing by turning of the animation
            RootPanel.get().addStyleName(RESIZE_ANIMATION_STOPPER);
            Scheduler.get().scheduleFixedDelay(() -> {
                RootPanel.get().removeStyleName(RESIZE_ANIMATION_STOPPER);
                return false;
            }, 400);
        });
    }

    @Override
    public void unload() {
        if (resize != null) {
            resize.removeHandler();
            resize = null;
        }
    }

    @Override
    public HandlerRegistration getResizeHandler() {
        return resize;
    }
}
