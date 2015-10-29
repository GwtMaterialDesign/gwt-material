package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

public class MaterialSlide extends ComplexWidget{

	private UnorderedList ul = new UnorderedList();

	public MaterialSlide(){
		setElement(Document.get().createDivElement());
		setStyleName("slider");
		ul.setStyleName("slides");
		super.add(ul);
		
	}
	
	
	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		String className = DOM.createUniqueId();
		addStyleName(className);
		initializeSlider(className);
	}
	

	@Override
	public void add(Widget child) {
		ul.add(child);
	}




	/**
	 * Initialize the slider when the widget is attached
	 * @param className CSS class name
	 */
	private native void initializeSlider(String className)/*-{
		$wnd.jQuery(document).ready(function() {
			$wnd.jQuery('.' + className).slider({
				full_width : true
			});
		});
	}-*/;
	
	/**
	 * Set the image slider to fullscreen view
	 * @param fullScreen 
	 */
	public void setFullScreen(boolean isFullscreen){
		if(isFullscreen){
			addStyleName("fullscreen");
		}else{
			removeStyleName("fullscreen");
		}
	}
}
