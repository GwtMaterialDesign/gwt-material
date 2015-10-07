package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ButtonBase;
import gwt.material.design.client.custom.ButtonType;
import gwt.material.design.client.custom.CustomSpan;
import gwt.material.design.client.custom.HasActivates;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.HasIcons;
import gwt.material.design.client.custom.HasType;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasText;

//@formatter:off
/**
* There are 3 main button types described in material design. The raised button is a standard button that signify actions and seek to give depth to a mostly flat page. The floating circular action button is meant for very important functions. Flat buttons are usually used within elements that already have depth like cards or modals.
* <p>
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* Raised (Default) Button
* <m:MaterialButton text="Button" waves="light" backgroundColor="blue" />
* 
* Adding icon
* <m:MaterialButton text="Button" waves="light" backgroundColor="blue" icon="cloud" iconPosition="left"/>
* 
* Floating Button
* <m:MaterialButton type="floating" waves="light" size="large"  icon="add"/>
* 
* Flat Button
* <m:MaterialButton text="Button" type="flat" waves="grey" />
* 
* Large Button
* <m:MaterialButton size="large" text="Button" waves="light" backgroundColor="blue" icon="cloud" iconPosition="right"/>
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#buttons">Material Button</a>
*/

//@formatter:on
public class MaterialButton extends ButtonBase implements HasText, HasGrid, HasActivates, HasWaves, HasColors, HasIcons, HasType{

	private MaterialIcon iconElem = new MaterialIcon();
	
	/**
	 * Creates an empty button
	 */
	public MaterialButton() {
		setElement(Document.get().createElement("button"));
		setStyleName("btn");
	}
	
	/**
	 * Raised buttons
	 * @param text - The text of the button
	 * @param color - The background color of the button. See the color palette here http://gwt-material.appspot.com/#colors
	 * @param waves - A material design aspect that helps user to add ripple effect on button
	 */
	public MaterialButton(String text, String bgColor, String waves) {
		this();
		setText(text);
		setBackgroundColor(bgColor);
		setWaves(waves);
	}
	
	/**
	 * Material Floating button
	 * @param icon - Icon to be applied. See reference here http://gwt-material.appspot.com/#icons
	 * @param color - The background color of the button. See the color palette here http://gwt-material.appspot.com/#colors
	 * @param type - Set it to floating
	 * @param waves - A material design aspect that helps user to add ripple effect on button
	 * @param tooltip - The tooltip to be shown when it is hovered
	 */
	public MaterialButton(String icon, String bgColor, String type, String waves, String tooltip, String tooltipLocation) {
		this();
		setType(type);
		setBackgroundColor(bgColor);
		setIcon(icon);
		setType(type);
		setWaves(waves);
		setTooltip(tooltip);
		setTooltipLocation(tooltipLocation);
	}

	/**
	 * Material Flat Button
	 * @param text - The text of the button
	 * @param type - Set it to flat
	 * @param icon - Icon to be applied. See reference here http://gwt-material.appspot.com/#icons
	 * @param iconPosition - Icon position can be left or right
	 * @param size - small, medium or large
	 * @param tooltip - The tooltip to be shown when it is hovered
	 */
	public MaterialButton(String text, String type, String icon, String iconPosition, String size, String tooltip, String tooltipLocation) {
		this();
		setText(text);
		setType(type);
		setIcon(icon);
		setIconPosition(iconPosition);
		setSize(size);
		setTooltip(tooltip);
		setTooltipLocation(tooltipLocation);
	}
	
	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		initWaves();
		initTooltip();
	}

	@Override
	public void setActivates(String activates) {
		this.getElement().setAttribute("data-activates", activates);
		this.addStyleName(activates + " dropdown-button");
	}

	@Override
	public void setGrid(String grid) {
		addStyleName("col " + grid);
	}

	@Override
	public void setOffset(String offset) {
		String tobeadded = "";
		String[] vals = offset.split(" ");
		for(String val : vals){
			tobeadded = tobeadded + " offset-" +  val;
		}
		this.addStyleName(tobeadded);
	}

	@Override
	public String getText() {
		return null;
	}

	@Override
	public void setText(String text) {
		add(new CustomSpan(text));
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
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text");
	}

	@Override
	public void setIcon(String icon) {
		iconElem.setIcon(icon);
		add(iconElem);
	}

	@Override
	public void setIconPosition(String iconPosition) {
		iconElem.setIconPosition(iconPosition);
	}

	/**
	 * Button size
	 * - Large
	 * - Medium (Default)
	 */
	public void setSize(String size) {
		addStyleName("btn-" + size);
	}

	@Override
	public void setType(String type) {
		ButtonType btnType = ButtonType.fromString(type);
		switch (btnType) {
		case FLAT:
			addStyleName("btn-flat");
			removeStyleName("btn");
			break;
		case FLOATING:
			addStyleName("btn-floating");
			break;
		default:
			// default : raised
			break;
		}
	}

	/* (non-Javadoc)
	 * @see gwt.material.design.client.custom.ButtonBase#setHref(java.lang.String)
	 */
	@Override
	public void setHref(String href) {
		getElement().setAttribute("data-href", href);
	}
	
	
}
