package gwt.material.design.client.pwa.pushnotifications;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

import static jsinterop.annotations.JsPackage.GLOBAL;

//@formatter:off

/**
 * Push Notification Core Services for Progressive Web Application
 *
 * @author kevzlou7979
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#notification">PWA Notification</a>
 */
//@formatter:on
@JsType(isNative = true, namespace = GLOBAL, name = "Push")
public class PushNotification {

    /**
     * Creates a simple push notification with title as param
     */
    @JsMethod
    public static native void create(String title);

    /**
     * Creates a push notification with additional options
     * such as icons, body, data etc.
     */
    @JsMethod
    public static native void create(String title, PushNotificationOptions options);

    /**
     * Return how many notifications are currently open.
     */
    @JsMethod
    public static native int count();

    /**
     * Will close / hide the Push Notification Dialog
     */
    @JsMethod
    public static native void close();

    /**
     * Will close / hide the Push Notification Dialog with tag as param
     */
    @JsMethod
    public static native void close(String tag);

    /**
     * Will clear all the notifications that are currently opened.
     */
    @JsMethod
    public static native void clear();
}
