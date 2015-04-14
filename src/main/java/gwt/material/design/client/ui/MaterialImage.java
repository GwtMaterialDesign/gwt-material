package gwt.material.design.client.ui;

import com.google.gwt.user.client.ui.Image;

public class MaterialImage extends Image{
	
	private String type="";
	private String caption="";

	public MaterialImage() {
		
	}
	
	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("responsive-img");
		onInitMaterialDesign();
		
	}
	
	public static native void onInitMaterialDesign()/*-{
		$wnd.jQuery(document).ready(function(){
	    	$wnd.jQuery('.materialboxed').materialbox();
	    });
	}-*/;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		this.addStyleName(type);
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
		this.getElement().setAttribute("data-caption", caption);
	}
	
	
}
