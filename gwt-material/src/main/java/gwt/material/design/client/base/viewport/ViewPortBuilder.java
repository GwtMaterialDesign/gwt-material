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
package gwt.material.design.client.base.viewport;

import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.js.Window;
import gwt.material.design.jquery.client.api.Functions;

import java.util.Arrays;
import java.util.List;

public class ViewPortBuilder {

    private HandlerRegistration windowResizeHandler;
    private List<ViewPort> viewPorts;
    private Functions.Func callback;
    private Functions.Func elseCallback;

    public ViewPortBuilder when(ViewPort... viewPort) {
        this.viewPorts = Arrays.asList(viewPort);
        return this;
    }

    public ViewPortBuilder then(Functions.Func callback) {
        this.callback = callback;
        return this;
    }

    public ViewPortBuilder orElse(Functions.Func elseCallback) {
        this.elseCallback = elseCallback;
        return this;
    }

    public void build() {
        // Must have the ability to check the viewport when the browser resizes
        windowResizeHandler = com.google.gwt.user.client.Window.addResizeHandler(resizeEvent -> {
            call(callback, elseCallback);
        });

        // Check at first load when the viewport matches the browser screensize.
        call(callback, elseCallback);
    }

    protected void call(Functions.Func callback, Functions.Func elseCallback) {
        boolean[] matches = {false};
        viewPorts.forEach(viewPort -> {
            if (matchMedia(viewPort.asMediaQuery())) {
                matches[0] = true;
            }
        });

        if (matches[0]) {
            if (callback != null) {
                callback.call();
            }
        } else {
            if (elseCallback != null) {
                elseCallback.call();
            }
        }
    }

    public void unLoad() {
        if (windowResizeHandler != null) {
            windowResizeHandler.removeHandler();
            windowResizeHandler = null;
        }
    }

    protected boolean matchMedia(String viewPortQuery) {
        return Window.matchMedia(viewPortQuery);
    }
}
