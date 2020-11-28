package gwt.material.design.client.js;

import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

@JsType(isNative = true, name = "Object", namespace = JsPackage.GLOBAL)
public class CopyCommandData {

    @JsProperty
    public String action;

    @JsProperty
    public String text;

    @JsProperty
    public String trigger;

    @JsMethod
    public native void clearSelection();
}
