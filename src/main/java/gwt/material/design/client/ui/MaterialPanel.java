package gwt.material.design.client.ui;

import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasShadow;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasVisibility;

//@formatter:off
/**
* A normal panel that has some material features like : shadow and ripple effect
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* <m:MaterialPanel color="white" waves="blue" shadow="3"/>
}
* </pre>
* @see <a href="http://gwt-material-demo.herokuapp.com/#shadow">Material Panels</a>
* @author kevzlou7979
*/
public class MaterialPanel extends HTMLPanel implements HasColors, HasWaves, HasShadow, HasVisibility{
	

	public MaterialPanel(String html) {
		super(html);
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		initWaves();
	}

	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text");
	}

	@Override
	public void setWaves(String waves) {
		addStyleName("waves-effect waves-" + waves);
	}

	@Override
	public native void initWaves()/*-{
	    $wnd.Waves.displayEffect();
	}-*/;

	@Override
	public void setShadow(int shadow) {
		this.addStyleName("z-depth-" + shadow);
	}
	
	/**
	 * Sets the name of your scrollspy
	 * @param scrollspy
	 */
	public void setScrollspy(String scrollspy){
		this.addStyleName("scrollspy section");
		this.getElement().setId(scrollspy);
	}
	
	/**
	 * Sets the opacity of the panel
	 * @param opacity
	 */
	public void setOpacity(int opacity){
		this.getElement().getStyle().setOpacity(opacity);
	}
	
	/**
	 * Sets the padding of the panel
	 * @param padding
	 */
	public void setPadding(String padding){
		this.getElement().getStyle().setPadding(Double.parseDouble(padding), Unit.PCT);
	}
	
	public void setAlign(String align){
		addStyleName("align-" + align);
	}
	
}
