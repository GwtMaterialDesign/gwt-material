package gwt.material.design.client.ui;

import gwt.material.design.client.custom.MaterialWidget;
import gwt.material.design.client.custom.UnorderedList;

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
	
	@UiField MaterialLink next, prev, lblName;
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
