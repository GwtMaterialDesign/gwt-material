package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ListItem;
import gwt.material.design.client.custom.UnorderedList;
import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.user.client.ui.Widget;

public class MaterialFloatingContainer extends MaterialPanel {

	private UnorderedList list = new UnorderedList();
	

	public MaterialFloatingContainer(SafeHtml safeHtml) {
		super(safeHtml);
	}

	public MaterialFloatingContainer(String tag, String html) {
		super(tag, html);
	}

	public MaterialFloatingContainer(String html) {
		super(html);
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName("fixed-action-btn");
		this.getElement().getStyle().setBottom(1, Unit.PCT);
		this.getElement().getStyle().setRight(1, Unit.PCT);
		this.addStyleName(MaterialResources.INSTANCE.materialcss().floatingButtonsItem());
		this.add(list);
		
		int ms = list.getWidgetCount() * 100;
		for(Widget w : list){
			if(w instanceof ListItem){
				MaterialButton buttom = (MaterialButton) ((ListItem) w).getWidget(0);
				buttom.getElement().getStyle().setOpacity(0);
				buttom.getElement().setAttribute("style", "transition-delay: "+ms+"ms;");
				ms -= 100;
			}
		}
	}

	@UiChild(tagname = "initial")
	public void addInitialButton(final Widget item) {
		
		this.add(item);
	}

	@UiChild(tagname = "item")
	public void addWidget(final Widget item) {
		
		list.add(new ListItem(item));
		
	}

}
