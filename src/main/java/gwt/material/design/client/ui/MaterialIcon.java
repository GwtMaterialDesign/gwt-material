package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ButtonBase;
import gwt.material.design.client.custom.HasActivates;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.HasIcons;
import gwt.material.design.client.custom.HasSeparator;
import gwt.material.design.client.custom.HasType;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;

public class MaterialIcon extends ButtonBase implements HasType, HasGrid, HasSeparator, HasActivates, HasIcons, HasAllMouseHandlers, HasColors{

	public MaterialIcon() {
		setElement(Document.get().createElement("i"));
		addStyleName("material-icons");
	}
	
	public MaterialIcon(String icon){
		this();
		setIcon(icon);
	}
	
	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onLoad()
	 */
	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		initTooltip();
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.Widget#onUnload()
	 */
	@Override
	protected void onUnload() {
		// TODO Auto-generated method stub
		super.onUnload();
	}

	@Override
	public void setActivates(String activates) {
		getElement().getStyle().setDisplay(Display.INLINE);
		getElement().setAttribute("data-activates", activates);
		addStyleName(activates + " dropdown-button");
	}

	@Override
	public void setSeparator(boolean separator) {
		if (separator) {
			this.getElement().setAttribute("style", "border-bottom: 1px solid #e9e9e9;");
		}
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
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
	public void setIcon(String icon) {
		getElement().setInnerHTML(icon);
	}


	@Override
	public void setIconPosition(String iconPosition) {
		addStyleName(iconPosition);
	}


	@Override
	public void setSize(String size) {
		addStyleName(size);
	}


	@Override
	public void setBackgroundColor(String bgColor) {
		addStyleName(bgColor);
	}


	@Override
	public void setTextColor(String textColor) {
		addStyleName(textColor + "-text");
	}


	@Override
	public void setType(String type) {
		if(type.equals("circle")){
			getElement().getStyle().setWidth(2, Unit.EM);
			getElement().getStyle().setPadding(0.5, Unit.EM);
			addStyleName("circle");
		}
	}

}
