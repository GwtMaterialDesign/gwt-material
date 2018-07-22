/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gwt.material.design.client.pwa.push.js;

import com.google.gwt.core.client.JavaScriptObject;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

import java.util.Date;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class NotificationOptions {

    /**
     * The actions array of the notification as specified in the options parameter of the constructor.
     */
    @JsProperty
    public String[] actions;

    /**
     * The URL of the image used to represent the notification when there is not enough space to display the
     * notification itself.
     */
    @JsProperty
    public String badge;

    /**
     * The body string of the notification as specified in the options parameter of the constructor.
     */
    @JsProperty
    public String body;

    /**
     * Returns a structured clone of the notification’s data.
     */
    @JsProperty
    public JavaScriptObject data;

    /**
     * The text direction of the notification as specified in the options parameter of the constructor.
     */
    @JsProperty
    public String dir;

    /**
     * The language code of the notification as specified in the options parameter of the constructor.
     */
    @JsProperty
    public String lang;

    /**
     * The ID of the notification (if any) as specified in the options parameter of the constructor.
     */
    @JsProperty
    public String tag;

    /**
     * The URL of the image used as an icon of the notification as specified in the options parameter of the constructor.
     */
    @JsProperty
    public String icon;

    /**
     * The  URL of an image to be displayed as part of the notification, as specified in the options parameter of
     * the constructor.
     */
    @JsProperty
    public String image;

    /**
     * Specifies whether the user should be notified after a new notification replaces an old one.
     */
    @JsProperty
    public boolean renotify;

    /**
     * A Boolean indicating that a notification should remain active until the user clicks or dismisses it, rather than
     * closing automatically.
     */
    @JsProperty
    public boolean requireInteraction;

    /**
     * Specifies whether the notification should be silent, i.e. no sounds or vibrations should be issued, regardless
     * of the device settings.
     */
    @JsProperty
    public boolean silent;

    /**
     * Specifies the time at which a notification is created or applicable (past, present, or future).
     */
    @JsProperty
    public Date timestamp;

    /**
     * The title of the notification as specified in the first parameter of the constructor.
     */
    @JsProperty
    public String title;

    /**
     * Specifies a vibration pattern for devices with vibration hardware to emit.
     */
    @JsProperty
    public boolean vibrate;
}
