package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class MaterialLink extends FocusPanel {

	protected HTMLPanel panel = new HTMLPanel("");
	private String text = "";
	private String icon = "";
	private String iconPosition = "";
	private String textColor = "";
	protected String fontSize = "";
	private String wave = "";
	private Object object;
	private String tooltip = "";
	private String tooltipLocation = "bottom";
	private String tooltipDelay = "50";
	private Widget container;
	
	private boolean active = false;

	public MaterialLink() {
		// TODO Auto-generated constructor stub
	}
	
	

	public MaterialLink(String text, String textColor) {
		super();
		setText(text);
		setTextColor(textColor);
	}

	

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		initToolTip();
	}



	public MaterialLink(String text) {
		super();
		this.text = text;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		generateLink();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
		generateLink();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		generateLink();
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		this.getElement().getStyle().setFontSize(Double.valueOf(fontSize), Unit.EM);
	}

	public String getIconPosition() {
		return iconPosition;
	}

	public void setIconPosition(String iconPosition) {
		this.iconPosition = iconPosition;
		generateLink();
	}

	public void generateLink() {
		this.clear();
		String iconMarkup = "";
		if (!icon.isEmpty()) {
			iconMarkup = "<i class='" + icon + " " + iconPosition + "'></i>";
		}
		panel = new HTMLPanel("<a class='" + textColor + "-text'>" + iconMarkup + " " + text + "</a>");
		panel.getElement().getStyle().setCursor(Cursor.POINTER);
		this.add(panel);
	}

	public String getWave() {
		return wave;
	}

	public void setWave(String wave) {
		this.wave = wave;
		if (!wave.isEmpty()) {
			this.addStyleName("waves-effect waves-" + wave);
		}
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getTooltip() {
		return tooltip;
	}
	
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
		generateTooltip();
	}

	public String getTooltipLocation() {
		return tooltipLocation;
	}

	public void setTooltipLocation(String tooltipLocation) {
		this.tooltipLocation = tooltipLocation;
		generateTooltip();
	}

	public String getTooltipDelay() {
		return tooltipDelay;
	}

	public void setTooltipDelay(String tooltipDelay) {
		this.tooltipDelay = tooltipDelay;
		generateTooltip();
	}

	private void generateTooltip(){
		this.addStyleName("tooltipped");
		this.getElement().setAttribute("data-position", tooltipLocation);
		this.getElement().setAttribute("data-delay", tooltipDelay);
		this.getElement().setAttribute("data-tooltip", tooltip);
	}

	public native void initToolTip()/*-{
		 $wnd.jQuery(document).ready(function(){
		    $wnd.jQuery('.tooltipped').tooltip({delay: 50});
		  });
	}-*/;
	
}
