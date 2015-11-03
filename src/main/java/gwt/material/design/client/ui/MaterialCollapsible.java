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

import gwt.material.design.client.constants.CollapsibleType;
import gwt.material.design.client.base.ComplexWidget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;

//@formatter:off
/**
 * Collapsibles are accordion elements that expand when clicked on.
 * They allow you to hide content that is not immediately relevant
 * to the user.
 *
 * <h3>UiBinder Usage:</h3>
 *
 * <pre>
 *{@code// Accordion
 * <m:MaterialCollapsible type="ACCORDION" grid="s12 m6 l8">
 *   <!-- ITEM 1 -->
 *   <m:MaterialCollapsibleItem>
 *     <m:MaterialCollapsibleHeader>
 *       <m:MaterialLink text="First" icon="polymer" iconPosition="left" textColor="black"/>
 *     </m:MaterialCollapsibleHeader>
 *     <m:MaterialCollapsibleBody>
 *       <m:MaterialLabel text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."/>
 *     </m:MaterialCollapsibleBody>
 *   </m:MaterialCollapsibleItem>
 * </m:MaterialCollapsible>
 *
 * // Expandable
 * <m:MaterialCollapsible type="EXPANDABLE" grid="s12 m6 l8">
 *   <!-- ITEM 1 -->
 *   <m:MaterialCollapsibleItem>
 *     <m:MaterialCollapsibleHeader>
 *       <m:MaterialLink text="First" icon="polymer" iconPosition="left" textColor="black"/>
 *     </m:MaterialCollapsibleHeader>
 *     <m:MaterialCollapsibleBody>
 *       <m:MaterialLabel text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."/>
 *     </m:MaterialCollapsibleBody>
 *   </m:MaterialCollapsibleItem>
 * </m:MaterialCollapsible>
 *
 * // Popout
 * <m:MaterialCollapsible type="POPOUT" grid="s12 m6 l8">
 *   <!-- ITEM 1 -->
 *	 <m:MaterialCollapsibleItem>
 *     <m:MaterialCollapsibleHeader>
 *       <m:MaterialLink text="First" icon="polymer" iconPosition="left" textColor="black"/>
 *     </m:MaterialCollapsibleHeader>
 *     <m:MaterialCollapsibleBody>
 *       <m:MaterialLabel text="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."/>
 *     </m:MaterialCollapsibleBody>
 *   </m:MaterialCollapsibleItem>
 * </m:MaterialCollapsible>
 * }
 * </pre>
 *
 * @author kevzlou7979
 * @see <a href="http://gwt-material-demo.herokuapp.com/#collapsibles">Material Collapsibles</a>
 */
//@formatter:on
public class MaterialCollapsible extends ComplexWidget {

	private int index;

	/**
	 * Creates an empty collapsible
	 */
	public MaterialCollapsible() {
		super(Document.get().createULElement());
		setStyleName("collapsible");
	}
	
	/**
	 *  Creates a list and adds the given widgets.
	 */
	public MaterialCollapsible(final MaterialCollapsibleItem... widgets){
		this();
		for (final MaterialCollapsibleItem item : widgets) {
            add(item);
        }
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		onInitCollapsible(getElement());
	}

	@Override
	protected void onUnload() {
		super.onUnload();
	}

	@Override
	public void add(Widget child) {
		super.add(child);
		onInitCollapsible(getElement());
	}

	/**
	 * Initialize the collapsible material component.
	 */
	private native void onInitCollapsible(final com.google.gwt.dom.client.Element e) /*-{
		$wnd.jQuery(document).ready(function(){
			$wnd.jQuery(e).collapsible();	
		})
	}-*/;

	public void setType(CollapsibleType type) {
		switch (type) {
		case POPOUT:
			this.getElement().setAttribute("data-collapsible", "accordion");
			this.addStyleName(type.getCssName());
			break;
		default:
			getElement().setAttribute("data-collapsible", type.getCssName());
			break;
		}
	}

	public void setActive(int index) {
		this.index = index;
		Widget activeWidget = getActive();
		if(activeWidget != null) {
			activeWidget.removeStyleName("active");
			activeWidget.addStyleName("active");
		}
	}

	public Widget getActive() {
		try {
			return getWidget(index);
		} catch (IndexOutOfBoundsException ex) {
			return null;
		}
	}
}
