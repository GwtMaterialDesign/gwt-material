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

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;

public class MaterialPagination<T> extends MaterialWidget {

	private static MaterialPaginationUiBinder uiBinder = GWT.create(MaterialPaginationUiBinder.class);

	@SuppressWarnings("rawtypes")
	interface MaterialPaginationUiBinder extends UiBinder<Widget, MaterialPagination> {
	}
	
	private int numberOfItems;
	private int displayItem;
	private int numberOfPage;
	
	private int initial = 0;
	private int last;
	private int currentPage = 1;
	
	private List<T> objects = new ArrayList<T>();
	
	@UiField MaterialLink3 next, prev, lblName;
	@UiField UnorderedList pager;

	public MaterialPagination() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}
	
	@UiHandler("next")
	void onNext(ClickEvent e){
		if(last < getNumberOfItems()) currentPage ++;
		getPageResult(currentPage);
	}
	
	@UiHandler("prev")
	void onPrev(ClickEvent e){
		if(initial > 1) currentPage --;
		getPageResult(currentPage);
	}

	public int getDisplayItem() {
		return displayItem;
	}

	public void setDisplayItem(int displayItem) {
		this.displayItem = displayItem;
	}

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public List<T> getObjects() {
		return objects;
	}

	public void setObjects(List<T> objects) {
		this.objects = objects;
		this.numberOfItems = objects.size();
		this.numberOfPage =  numberOfItems / displayItem;
		this.getPageResult(1);
	}

	public List<T> getPageResult(int currentPage){
		List<T> displayObj = new ArrayList<T>();
		
		last = displayItem * currentPage;
		initial = (last - displayItem) + 1;
		System.out.println(initial + ":" + last);
		
		lblName.setText(initial + "-" + last + " of " + getNumberOfItems());
	
		
		return displayObj;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}
