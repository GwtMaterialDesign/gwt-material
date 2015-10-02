package gwt.material.design.client.ui;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


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
