package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.HasColors;
import gwt.material.design.client.custom.HasDisabled;
import gwt.material.design.client.custom.HasGrid;
import gwt.material.design.client.custom.HasWaves;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.HasWidgets;

public class MaterialTabItem extends ComplexWidget implements HasWidgets, HasColors, HasWaves, HasGrid, HasDisabled{

	public MaterialTabItem() {
		setElement(Document.get().createLIElement());
		setStyleName("tab");
	}
	
	@Override
	protected void onLoad() {
		// TODO Auto-generated method stub
		super.onLoad();
		initWaves();
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
	public void setWaves(String waves) {
		addStyleName("waves-"+waves + " waves-effect");
	}

	@Override
	public native void initWaves()/*-{
	    $wnd.Waves.displayEffect();
	}-*/;

	@Override
	public void setBackgroundColor(String bgColor) {
		// TODO Auto-generated method stub
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		// TODO Auto-generated method stub
		addStyleName(textColor+"-text");
	}

	@Override
	public void setDisabled(boolean disabled) {
		if(disabled)addStyleName("disabled");
		else removeStyleName("disabled");
	}

	@Override
	public boolean isDisabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
