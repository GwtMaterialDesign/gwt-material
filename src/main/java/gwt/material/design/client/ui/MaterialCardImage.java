package gwt.material.design.client.ui;

import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
* Card Element for card image. 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#cards">Material Cards</a>
*/

//@formatter:on
public class MaterialCardImage extends ComplexPanel implements HasWaves{

	public MaterialCardImage(){
		setElement(Document.get().createDivElement());
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public void add(final Widget child) {
    	if(child instanceof MaterialImage){
    		child.addStyleName("activator");
    	}
        add(child, (Element) getElement());
    }

    /**
     * Inserts a widget at a specific index
     *
     * @param child       - widget to be inserted
     * @param beforeIndex - index for the widget
     */
    public void insert(final Widget child, final int beforeIndex) {
        insert(child, (Element) getElement(), beforeIndex, true);
    }
	
	@Override
	public void setWaves(String waves) {
		addStyleName("waves-effect waves-" + waves);
		initWaves();
	}
	
	@Override
	public native void initWaves()/*-{
	    $wnd.Waves.displayEffect();
	}-*/;
	
	
}