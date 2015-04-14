package gwt.material.design.client.custom;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MaterialWidget extends Composite{
	
	protected String waves = "";
	protected String color = "";
	protected String textColor = "";
	protected String type = "";
	protected Widget widget;
	protected String align = "";
	protected String tooltip = "";
	protected String tooltipLocation = "bottom";
	protected String tooltipDelay = "50";
	protected boolean disable;
	private String padding;
	
	public String getWaves() {
		return waves;
	}

	public void setWaves(String waves) {
		this.waves = waves;
		applyMaterialEffect();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
		applyMaterialEffect();
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
		applyMaterialEffect();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void applyMaterialEffect() {
		
		if(widget!=null){
			if(!isDisable()){
				if(!waves.isEmpty()) widget.getElement().addClassName("waves-effect waves-" + waves);
				if(!color.isEmpty()) widget.getElement().addClassName(color);
				if(!textColor.isEmpty()) widget.getElement().addClassName(textColor + "-text");
				if(!align.isEmpty()) widget.getElement().addClassName(align + "-align");
					
				if(!tooltip.isEmpty()) getWidget().addStyleName("tooltipped");
				if(!tooltipLocation.isEmpty()) getWidget().getElement().setAttribute("data-position", tooltipLocation);
				if(!tooltipDelay.isEmpty()) getWidget().getElement().setAttribute("data-delay", tooltipDelay);
				if(!tooltip.isEmpty()) getWidget().getElement().setAttribute("data-tooltip", tooltip);
			}else{
				widget.addStyleName("disabled");
				widget.getElement().addClassName("grey lighten-2 ");
			}
			
		}
		initToolTip();
	}

	public Widget getWidget() {
		return widget;
	}

	public void setWidget(Widget widget) {
		this.widget = widget;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	
	public String getTooltip() {
		return tooltip;
	}
	
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
		applyMaterialEffect();
	}

	public String getTooltipLocation() {
		return tooltipLocation;
	}

	public void setTooltipLocation(String tooltipLocation) {
		this.tooltipLocation = tooltipLocation;
		applyMaterialEffect();
	}

	public String getTooltipDelay() {
		return tooltipDelay;
	}

	public void setTooltipDelay(String tooltipDelay) {
		this.tooltipDelay = tooltipDelay;
		applyMaterialEffect();
	}

	private native void initToolTip()/*-{
		 $wnd.jQuery(document).ready(function(){
		    $wnd.jQuery('.tooltipped').tooltip({delay: 50});
		  });
	}-*/;

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public String getPadding() {
		return padding;
	}

	public void setPadding(String padding) {
		this.padding = padding;
		this.getElement().getStyle().setPadding(Double.parseDouble(padding), Unit.PCT);
	}
	
	
	
}
