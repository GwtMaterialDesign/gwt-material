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

import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.jquery.client.api.Promise;
import jsinterop.annotations.*;

/**
 * The Notification interface of the Notifications API is used to configure and display desktop notifications to the user.
 * This is an alternative if you are not using external client notification libraries like FCM (FireBaseCloudMessaging).
 * See all the notification display options at {@link NotificationOptions}.
 * <pre>
 *      // Will request a notification
 *      Notification.requestPermission(status -> MaterialToast.fireToast("Status: " + status));
 *
 *      if (Notification.getPermission().equals("granted")) {
 *          NotificationOptions options = new NotificationOptions();
 *          options.body = "I love GMD";
 *          options.icon = "https://user.oc-static.com/upload/2017/05/03/14938342186053_01-duration-and-easing.png";
 *          new Notification("GMD Says", options);
 *       } else {
 *          MaterialToast.fireToast("Permission Denied. Update it thru the browser setting");
 *       }
 * </pre>
 *
 * @author kevzlou7979
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class Notification {

    @JsConstructor
    public Notification(String message) {
    }

    @JsConstructor
    public Notification(String message, NotificationOptions notification) {
    }

    @JsProperty
    private static String permission;

    @JsProperty
    private Functions.Func1<Object> onclick;

    @JsProperty
    private Functions.Func1<Object> onclose;

    @JsProperty
    private Functions.Func1<Object> onerror;

    @JsProperty
    private Functions.Func1<Object> onshow;

    /**
     * A string representing the current permission to display notifications.
     * <ul>
     * <li>denied — The user refuses to have notifications displayed.</li>
     * <li>granted — The user accepts having notifications displayed.</li>
     * <li>default — The user choice is unknown and therefore the browser will act as if the value were denied.</li>
     * </ul>
     */
    @JsOverlay
    public static final String getPermission() {
        return permission;
    }

    /**
     * A handler for the click event. It is triggered each time the user clicks on the notification.
     */
    @JsOverlay
    public final void setOnclick(Functions.Func1<Object> onclick) {
        this.onclick = onclick;
    }

    /**
     * A handler for the close event. It is triggered when the user closes the notification.
     */
    @JsOverlay
    public final void setOnclose(Functions.Func1<Object> onclose) {
        this.onclose = onclose;
    }

    /**
     * A handler for the error event. It is triggered each time the notification encounters an error.
     */
    @JsOverlay
    public final void setOnerror(Functions.Func1<Object> onerror) {
        this.onerror = onerror;
    }

    /**
     * A handler for the show event. It is triggered when the notification is displayed.
     */
    @JsOverlay
    public final void setOnshow(Functions.Func1<Object> onshow) {
        this.onshow = onshow;
    }

    /**
     * Requests permission from the user to display notifications.
     */
    @JsMethod
    public static native Promise requestPermission(Functions.Func1<String> status);

    /**
     * Programmatically closes a notification.
     */
    @JsMethod
    public static native void close();
}
