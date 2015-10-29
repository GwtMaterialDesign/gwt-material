package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.DOM;

public class MaterialSlide extends ComplexWidget{

	private UnorderedList ul = new UnorderedList();

	public MaterialSlide(){
		setElement(Document.get().createDivElement());
		setStyleName("slider");
		ul.setStyleName("slides");
		add(ul);
		String className = DOM.createUniqueId();
		addStyleName(className);
		initializeSlider(className);
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
