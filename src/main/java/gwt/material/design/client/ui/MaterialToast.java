package gwt.material.design.client.ui;

public class MaterialToast {

	public MaterialToast() {
		// TODO Auto-generated constructor stub
	}
	
	public static native void alert(String msg) /*-{
	  $wnd.toast(msg, 4000)
	}-*/;
	
}
