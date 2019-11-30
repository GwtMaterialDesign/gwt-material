package gwt.material.design.client.js;

import gwt.material.design.jquery.client.api.Functions;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class MediaQueryList {

    @JsProperty
    public boolean matches;

    @JsProperty
    public String media;

    @JsMethod
    public native void addListener(Functions.Func1<MediaQueryEvent> event);
}
