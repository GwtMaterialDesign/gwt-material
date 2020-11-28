package gwt.material.design.client.js;

import gwt.material.design.jquery.client.api.Functions;
import gwt.material.design.jquery.client.api.JQueryElement;
import jsinterop.annotations.JsConstructor;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsType;

@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class ClipboardJS {

    @JsConstructor
    public ClipboardJS(String className) {
    }

    public native JQueryElement on(String eventName, Functions.Func1<CopyCommandData> event);
}
