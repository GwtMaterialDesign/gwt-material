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

import gwt.material.design.client.base.HasHref;
import gwt.material.design.client.ui.html.ListItem;

//@formatter:off

/**
 * Item for Tab Component, which usually contains icons, links or other navigation component.
 * <h3>UiBinder Usage:</h3>
 * <pre>
 *{@code<m:MaterialTabItem waves="YELLOW" grid="l4"><i:Link text="Tab 1" href="#tab1" textColor="white"/></m:MaterialTabItem>}
 * </pre>
 * @see <a href="http://gwt-material-demo.herokuapp.com/#tabs">Material Tabs</a>
 * @author kevzlou7979
 * @author Ben Dol
 */
//@formatter:on
public class MaterialTabItem extends ListItem {

	private MaterialTab parent;

	public MaterialTabItem() {
		super();
		setStyleName("tab");
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		try {
			parent = (MaterialTab)getParent();
		} catch (ClassCastException ex) {
			throw new ClassCastException(
				"MaterialTabItem must be within a MaterialTab widget.");
		}
	}

	public void selectTab() {
		if(getChildren().size() > 0) {
			try {
				HasHref child = (HasHref) getChildren().get(0);
				parent.selectTab(child.getHref().replaceAll("[^a-zA-Z\\d\\s:]", ""));
			} catch (ClassCastException ex) {
				throw new ClassCastException(
					"MaterialTabItem must be compatible with HasHref.");
			}
		}
	}
}
