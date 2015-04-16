package gwt.material.design.client.ui;

import gwt.material.design.client.custom.MaterialWidget;
import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class MaterialDropDown extends MaterialWidget {

	private static MaterialDropDownUiBinder uiBinder = GWT.create(MaterialDropDownUiBinder.class);

	interface MaterialDropDownUiBinder extends UiBinder<Widget, MaterialDropDown> {
	}

	private String text = "";
	private String activates = "";
	private boolean divider;

	@UiField
	MaterialButton anchor;
	@UiField
	UnorderedList list;

	public MaterialDropDown() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		activates = String.valueOf(hashCode());
		if(!activates.isEmpty()){
			anchor.getElement().setAttribute("data-activates", activates);
			list.getElement().setId(activates);
		}
		
		super.setWidget(anchor.getButton());
		super.applyMaterialEffect();
		initDropDown();
		
	}

	public native void initDropDown()/*-{
		$wnd.jQuery('.dropdown-button').dropdown({
			inDuration : 300,
			outDuration : 225,
			constrain_width : true, // Does not change width of dropdown to that of the activator
			hover : false, // Activate on click
			alignment : 'top', // Aligns dropdown to left or right edge (works with constrain_width)
			gutter : 0, // Spacing from edge
			belowOrigin : false
		// Displays dropdown below the button
		});
	}-*/;

	@UiChild(tagname = "item")
	public void addWidget(final Widget item) {
		list.add(new ListItem(item));
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		if (!text.isEmpty())
			anchor.setText(text);
		list.addStyleName(MaterialResources.INSTANCE.materialcss().collection());
	}

	public boolean isDivider() {
		return divider;
	}

	public void setDivider(boolean divider) {
		this.divider = divider;
		if(isDivider()){
			for(Widget w : list){
				if(w instanceof ListItem){
					w.getElement().setAttribute("style", "border-bottom: 1px solid #e9e9e9;");
				}
			}
		}
		
	}

	public String getActivates() {
		return activates;
	}

	public void setActivates(String activates) {
		this.activates = activates;
	}

}
