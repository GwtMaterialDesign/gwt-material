package gwt.material.design.client.ui;

import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCollapsible extends UnorderedList {

	private String wave = "";
	private String type = "";
	
	public MaterialCollapsible() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.getElement().addClassName("collapsible");
		onInitCollapsible();
	}

	@UiChild(tagname = "item")
	public void addItem(final Widget item) {
		if(!wave.isEmpty()){
			item.addStyleName("waves-effect waves-" + wave);
		}
		this.add(item);
	}
	
	
	public static native void onInitCollapsible()/*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('.collapsible').collapsible({
				accordion : false
			});
		});
	}-*/;

	public String getWave() {
		return wave;
	}

	public void setWave(String wave) {
		this.wave = wave;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if(type.equals("popout")){
			this.getElement().setAttribute("data-collapsible", "accordion");
			this.addStyleName(type);
		}else{
			this.getElement().setAttribute("data-collapsible", type);
		}
	}

	
	
}
