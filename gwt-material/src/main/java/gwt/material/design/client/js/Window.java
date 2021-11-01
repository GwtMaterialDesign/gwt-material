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
package gwt.material.design.client.js;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import gwt.material.design.client.base.DeferredPrompt;
import gwt.material.design.jquery.client.api.Functions;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

@JsType
public class Window {

    //TODO: Convert to JSInterop
    public static native boolean matchMedia(String query) /*-{
        return $wnd.window.matchMedia(query).matches;
    }-*/;

    //TODO: Convert to JSInterop
    public static native MediaQueryList getMediaQueryList(String query) /*-{
        return $wnd.window.matchMedia(query);
    }-*/;
    
    @JsMethod(namespace = "window")
    public static native void addEventListener(String eventName, Functions.Func1<Object> event);

    @JsMethod(namespace = "window")
    public static native String atob(String param);

    @JsMethod(namespace = "window")
    public static native String unescape(String param);

    @JsMethod
    public static native DeferredPrompt deferredPrompt();

    public static HandlerRegistration addResizeHandler(ResizeHandler handler) {
        return com.google.gwt.user.client.Window.addResizeHandler(handler);
    }

}
