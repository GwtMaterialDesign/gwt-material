package gwt.material.design.client.ui;

import java.util.Iterator;

import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class MaterialNavSection extends UnorderedList implements HasWidgets{

	/**
	 * Container for App Toolbar and App Sidebar , contains Material Links, Icons or any other material components
	 */
	@UiConstructor
	public MaterialNavSection(String align) {
		this.addStyleName(align + " hide-on-med-and-down");
	}

	@Override
	public void add(Widget w) {
		ListItem item = new ListItem(w);
		super.add(item);
	}

	@Override
	public void clear() {
		this.clear();
	}

	@Override
	public Iterator<Widget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Widget w) {
		// TODO Auto-generated method stub
		return false;
	}

}
