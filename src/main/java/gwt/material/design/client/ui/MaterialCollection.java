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

import gwt.material.design.client.custom.MaterialWidget;
import gwt.material.design.client.resources.MaterialResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
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
		// TODO Auto-generated method stub
		super.onAttach();
		this.addStyleName(MaterialResources.INSTANCE.materialcss().collection());
	}



	/**
	 * Add each item on a collection container
	 * @param item, the item to be added
	 */
	@UiChild(tagname = "item")
	public void addCollectionItem(Widget item){
		ListItem list = new ListItem(item);
		list.addStyleName("collection-item");
		collection.add(list);
	}
	
	@UiChild(tagname = "header")
	public void addHeader(Widget item){
		ListItem list = new ListItem(item);
		list.addStyleName(MaterialResources.INSTANCE.materialcss().collectionHeader());
		collection.add(list);
	}
	
	@UiChild(tagname = "dismissable")
	public void addDismissableItem(Widget item){
		ListItem list = new ListItem(item);
		list.addStyleName("collection-item dismissable");
		collection.add(list);
	}
	
	@UiChild(tagname = "avatar")
	public void addAvatarItem(Widget item){
		ListItem list = new ListItem(item);
		list.addStyleName("collection-item avatar");
		collection.add(list);
		if(item instanceof MaterialPanel){
			HTMLPanel panel = (HTMLPanel) item;
			for(Widget w : panel){
				if(w instanceof MaterialIcon){
					w.addStyleName("secondary-content");
				}
			}
		}
	}
	
	public void clear(){
		collection.clear();
	}

}
