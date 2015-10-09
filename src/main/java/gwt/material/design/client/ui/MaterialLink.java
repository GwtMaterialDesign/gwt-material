package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ButtonBase;
import gwt.material.design.client.custom.CustomSpan;
import gwt.material.design.client.custom.HasIcons;
import gwt.material.design.client.custom.HasSeparator;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
* 
* <p>Using Material Link you can easily add href functionality into your app for navigation
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
* Links
* <m:MaterialLink2 href="#normal" text="Normal Link" textColor="red" icon="polymer" iconPosition="left" grid=" "/>
* 
* <m:MaterialLink2 href="#material" text="Link with Href" textColor="red" icon="polymer" iconPosition="left" grid=" "/>
* 
* <m:MaterialLink2 href="#design" text="Link with Different Icon color" textColor="black" icon="polymer" iconPosition="left" iconColor="red" grid=" "/>}
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#buttons">Material Link</a>
*/
public class MaterialLink extends ButtonBase implements HasIcons, HasSeparator{

	private MaterialIcon iconElem = new MaterialIcon();
	private CustomSpan spanElem = new CustomSpan();
	private String text;

	/**
	 * Create an empty link
	 */
	public MaterialLink() {
		setElement(Document.get().createElement("a"));
		getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		getElement().getStyle().setCursor(Cursor.POINTER);
	}
	
	/**
	 * Add badge element into your material link
	 * @param badge
	 */
	public MaterialLink(final Widget... widgets){
		this();
		for (final Widget w : widgets) {
            add(w);
        }
	}
	
	
	/**
	 * Normal link with href
	 * @param text
	 * @param href
	 */
	public MaterialLink(String text, String href) {
		this();
		setText(text);
		setHref(href);
	}


	/**
	 * Creates a link with icon
	 * @param text
	 * @param href
	 * @param textColor
	 */
	public MaterialLink(String text, String href, String icon) {
		this();
		setText(text);
		setHref(href);
		setIcon(icon);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		spanElem.setText(text);
		add(spanElem);
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

	public void setSize(String size) {
		addStyleName(size);
	}
	
	@Override
	public void setIconColor(String iconColor) {
		iconElem.addStyleName(iconColor + "-text");
	}
	
	public void setFontSize(double fontSize) {
		spanElem.getElement().getStyle().setFontSize(fontSize, Unit.EM);
	}

	@Override
	public void setSeparator(boolean separator) {
		if(separator){
			this.getElement().getStyle().setProperty("borderBottom", "1px solid #e9e9e9");
		}else{
			this.getElement().getStyle().setProperty("borderBottom", "none");
		}
	}
	
}
