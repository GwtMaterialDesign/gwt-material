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

import gwt.material.design.client.custom.MaterialWidget;
import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class MaterialCollection extends MaterialWidget {

	private static MaterialCollectionUiBinder uiBinder = GWT.create(MaterialCollectionUiBinder.class);

	interface MaterialCollectionUiBinder extends UiBinder<Widget, MaterialCollection> {
	}

	@UiField
	UnorderedList collection;

	public MaterialCollection() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		this.addStyleName(MaterialResources.INSTANCE.materialcss().collection());
	}

	/**
	 * Add new item on the collection container. Implicit as well assigns a
	 * press indication.
	 * 
	 * @param item
	 *            The widget to be added to the collection container
	 * @return The created ListItem, this might be used to add DomHandlers (eg.
	 *         ClickHandlers etc.) and to react therefore on the full widget
	 *         panel
	 */
	@UiChild(tagname = "item")
	public ListItem addCollectionItem(Widget item) {
		ListItem listItem = new ListItem(item);
		listItem.addStyleName("collection-item");
		MaterialUiHelper.addMousePressedHandlers(listItem);
		collection.add(listItem);
		return listItem;
	}

	/**
	 * Insert item on a collection container at the given index. Implicit as
	 * well assigns a press indication.
	 * 
	 * @param item
	 *            The widget to be inserted into the collection container
	 * @param beforeIndex
	 *            The location to insert the widget into the collection
	 * @return The created ListItem, this might be used to add DomHandlers (eg.
	 *         ClickHandlers etc.) and to react therefore on the full widget
	 *         panel
	 */
	public ListItem insertCollectionItem(Widget item, int beforeIndex) {
		ListItem listItem = new ListItem(item);
		listItem.addStyleName("collection-item");
		MaterialUiHelper.addMousePressedHandlers(listItem);
		collection.insert(listItem, beforeIndex);
		return listItem;
	}

	/**
	 * Adds an item to the collection styled as header row. No PressedHandler/Indication is
	 * applied here.
	 * 
	 * @param item The widget to be added to the put at the top of the collection container
	 * @return The created ListItem, this might be used to add DomHandlers (eg.
	 *         ClickHandlers etc.) and to react therefore on the full widget
	 *         panel
	 */
	@UiChild(tagname = "header")
	public ListItem addHeader(Widget item) {
		ListItem listItem = new ListItem(item);
		listItem.addStyleName(MaterialResources.INSTANCE.materialcss().collectionHeader());
		collection.add(listItem);
		return listItem;
	}

	/**
	 * Add new dismissable item on the collection container. Implicit as well
	 * assigns a press indication.
	 * 
	 * @param item
	 *            The widget item to be added
	 * @return The created ListItem, this might be used to add DomHandlers (eg.
	 *         ClickHandlers etc.) and to react therefore on the full widget
	 *         panel
	 */
	@UiChild(tagname = "dismissable")
	public ListItem addDismissableItem(Widget item) {
		ListItem listItem = new ListItem(item);
		listItem.addStyleName("collection-item dismissable");
		MaterialUiHelper.addMousePressedHandlers(listItem);
		collection.add(listItem);
		return listItem;
	}

	/**
	 * Insert new item on the collection container. Implicit as well assigns a
	 * press indication.
	 * 
	 * @param item
	 *            The widget item to be inserted
	 * @param beforeIndex
	 *            The location to be inserted
	 * @return The created ListItem, this might be used to add DomHandlers (eg.
	 *         ClickHandlers etc.) and to react therefore on the full widget
	 *         panel
	 */
	public ListItem insertDismissableItem(Widget item, int beforeIndex) {
		ListItem listItem = new ListItem(item);
		listItem.addStyleName("collection-item dismissable");
		MaterialUiHelper.addMousePressedHandlers(listItem);
		collection.insert(listItem, beforeIndex);
		return listItem;
	}

	/**
	 * Add new Avatar item on the collection container. Implicit as well assigns
	 * a press indication.
	 * 
	 * @param item
	 *            The widget item to be added
	 * @return The created ListItem, this might be used to add DomHandlers (eg.
	 *         ClickHandlers etc.) and to react therefore on the full widget
	 *         panel
	 */
	@UiChild(tagname = "avatar")
	public ListItem addAvatarItem(Widget item) {
		ListItem listItem = new ListItem(item);
		listItem.addStyleName("collection-item avatar");
		MaterialUiHelper.addMousePressedHandlers(listItem);
		collection.add(listItem);
		if (item instanceof MaterialPanel) {
			HTMLPanel panel = (HTMLPanel) item;
			for (Widget w : panel) {
				if (w instanceof MaterialIcon) {
					w.addStyleName("secondary-content");
				}
			}
		}
		return listItem;
	}

	/**
	 * Insert new Avatar item on the collection container. Implicit as well
	 * assigns a press indication.
	 * 
	 * @param item
	 *            The item to be added
	 * @param beforeIndex
	 *            The location to insert the widget item
	 * @return The created ListItem, this might be used to add DomHandlers (eg.
	 *         ClickHandlers etc.) and to react therefore on the full widget
	 *         panel
	 */
	public ListItem insertAvatarItem(Widget item, int beforeIndex) {
		ListItem listItem = new ListItem(item);
		listItem.addStyleName("collection-item avatar");
		MaterialUiHelper.addMousePressedHandlers(listItem);
		collection.insert(listItem, beforeIndex);
		if (item instanceof MaterialPanel) {
			HTMLPanel panel = (HTMLPanel) item;
			for (Widget w : panel) {
				if (w instanceof MaterialIcon) {
					w.addStyleName("secondary-content");
				}
			}
		}
		return listItem;
	}

	public void clear() {
		collection.clear();
	}

	public Widget getWidget(int index) {
		return getFirstChild(collection.getWidget(index));
	}

	public int getWidgetCount() {
		return collection.getWidgetCount();
	}

	public int getWidgetIndex(Widget child) {
		return collection.getWidgetIndex(child.getParent());
	}

	public int getWidgetIndex(IsWidget child) {
		return getWidgetIndex(asWidgetOrNull(child).getParent());
	}

	private Widget getFirstChild(Widget parent) {
		if (parent instanceof HasWidgets) {
			Iterator<Widget> iter = ((HasWidgets) parent).iterator();
			return (iter != null && iter.hasNext()) ? iter.next() : null;
		}
		return null;
	}

}
