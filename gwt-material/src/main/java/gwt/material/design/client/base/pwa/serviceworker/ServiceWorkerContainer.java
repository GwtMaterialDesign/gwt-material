package gwt.material.design.client.base.pwa.serviceworker;


import gwt.material.design.jscore.client.api.promise.Promise;
import jsinterop.annotations.JsMethod;
import jsinterop.annotations.JsType;

//TODO Need to transfer this at GMD JQuery
@JsType(isNative=true, name="serviceWorker")
public class ServiceWorkerContainer {
	
	@JsMethod
	public native String toString();
	
	@JsMethod
	public native Promise register(String scriptName);

}