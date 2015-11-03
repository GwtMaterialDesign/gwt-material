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

import gwt.material.design.client.custom.ComplexNav;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
* 
* <p>Material NavSection is a child of MaterialNavBar that will contain toolbar items such as link, icon and other components
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
<m:MaterialNavSection align="right">
	<m:MaterialLink  icon="mdi-action-account-circle" iconPosition="left" text="Account"  textColor="white" waves="light"/>
	<m:MaterialLink  icon="mdi-action-autorenew" iconPosition="left" text="Refresh" textColor="white" waves="light"/>
	<m:MaterialLink  icon="mdi-action-search" tooltip="Menu" textColor="white" waves="light"/>
	<m:MaterialLink  icon="mdi-navigation-more-vert" tooltip="Starter" textColor="white" waves="light"/>
</m:MaterialNavSection>
}
</pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#loaders">Material Loaders</a>
*/
//@formatter:on
public class MaterialNavSection extends ComplexNav {

	/**
	 * Container for App Toolbar and App Sidebar , contains Material
	 * Links, Icons or any other material components.
	 */
	public MaterialNavSection() {
		setElement(Document.get().createULElement());
		setStyleName("hide-on-med-and-down");
	}
	
	/**
	 *  Creates a list and adds the given widgets.
	 */
	public MaterialNavSection(final Widget... widgets) {
		this();
		for (final Widget item : widgets) {
			ListItem li = new ListItem(item);
            add(li);
        }
	}
}
